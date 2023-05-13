package com.dato.push.app.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.dato.push.app.dao.SysMenu;
import com.dato.push.app.dao.SysRole;
import com.dato.push.app.dao.SysRoleMenu;
import com.dato.push.app.dao.SysUser;
import com.dato.push.app.exception.UnregisteredException;
import com.dato.push.app.mapper.SysMenuMapper;
import com.dato.push.app.mapper.SysRoleMapper;
import com.dato.push.app.mapper.SysRoleMenuMapper;
import com.dato.push.app.model.LoginUser;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dato.push.app.dao.table.Tables.*;

/**
 * 用户信息实现加载类
 */
@Component
public class LoginUserDetailsService implements UserDetailsService {
    @Resource
    private UserService userService;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.getUserByAccount(username);
        LoginUser loginUser = BeanUtil.copyProperties(sysUser, LoginUser.class);

        if (Objects.isNull(loginUser)){
            throw UnregisteredException.noRegister();
        }

        // 获取角色key
        String roleKeys = loginUser.getRoleKeys();
        if (StringUtils.isBlank(roleKeys)){
            return loginUser;
        }

        // 获取权限id
        QueryWrapper query1 = QueryWrapper.create();
        query1.where(SYS_ROLE.ROLE_KEY.in(StrUtil.split(roleKeys, ",")));
        List<SysRole> sysRoles = sysRoleMapper.selectListByQuery(query1);
        if (CollectionUtils.isEmpty(sysRoles)){
            return loginUser;
        }

        // 权限id集合
        List<Long> roleIdList = sysRoles.stream().map(SysRole::getId).collect(Collectors.toList());
        // 获取菜单id
        QueryWrapper query2 = QueryWrapper.create();
        query2.where(SYS_ROLE_MENU.ROLE_ID.in(roleIdList));
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectListByQuery(query2);
        if (CollectionUtils.isEmpty(sysRoleMenus)){
            return loginUser;
        }
        List<Long> meauIdList = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());

        // 获取菜单对应的权限
        QueryWrapper query3 = QueryWrapper.create();
        query3.where(SYS_MENU.ID.in(meauIdList));
        List<SysMenu> sysMenuList = sysMenuMapper.selectListByQuery(query3);
        if (CollectionUtils.isEmpty(sysMenuList)){
            return loginUser;
        }

        List<String> permissionList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            String perms = sysMenu.getPerms();
            if (StringUtils.isNotBlank(perms)){
                // 获取菜单所需要的权限
                List<String> permsList = StrUtil.split(perms, ",");
                permissionList.addAll(permsList);
            }
        }

        loginUser.setPermission(permissionList);
        return loginUser;
    }
}
