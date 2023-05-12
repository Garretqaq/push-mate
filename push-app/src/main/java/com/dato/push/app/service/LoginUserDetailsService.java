package com.dato.push.app.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.dato.push.app.dao.SysRole;
import com.dato.push.app.dao.SysRoleMenu;
import com.dato.push.app.dao.SysUser;
import com.dato.push.app.mapper.SysRoleMapper;
import com.dato.push.app.mapper.SysRoleMenuMapper;
import com.dato.push.app.mapper.SysUserMapper;
import com.dato.push.app.model.LoginUser;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import javax.annotation.Resource;

import java.util.List;

import static com.dato.push.app.dao.table.Tables.*;

@Component
public class LoginUserDetailsService implements UserDetailsService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper query = QueryWrapper.create();
        query.where(SYS_USER.ACCOUNT.eq(username));
        SysUser sysUser = sysUserMapper.selectOneByQuery(query);
        LoginUser loginUser = BeanUtil.copyProperties(sysUser, LoginUser.class);

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
        List<Long> roleIdList = sysRoles.stream().map(SysRole::getId).toList();
        // 获取菜单id
        QueryWrapper query2 = QueryWrapper.create();
        query2.where(SYS_ROLE_MENU.ROLE_ID.in(roleIdList));
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectListByQuery(query2);
        if (CollectionUtils.isEmpty(sysRoleMenus)){
            return loginUser;
        }
        sysRoleMenus.stream().map(SysRoleMenu::getMenuId)
        return null;
    }
}
