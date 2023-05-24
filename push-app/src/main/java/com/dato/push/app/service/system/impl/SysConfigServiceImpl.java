package com.dato.push.app.service.system.impl;

import cn.hutool.core.bean.BeanUtil;
import com.dato.push.app.dao.SysMenu;
import com.dato.push.app.dao.SysRole;
import com.dato.push.app.dao.SysRoleMenu;
import com.dato.push.app.dao.SysUser;
import com.dato.push.app.dao.mapper.SysMenuMapper;
import com.dato.push.app.dao.mapper.SysRoleMenuMapper;
import com.dato.push.app.model.common.NoData;
import com.dato.push.app.model.common.ResponseResult;
import com.dato.push.app.model.system.req.AddRoleRequest;
import com.dato.push.app.model.system.req.AuthMenuRequest;
import com.dato.push.app.model.system.req.AuthRoleRequest;
import com.dato.push.app.model.system.req.UserDetailRequest;
import com.dato.push.app.service.system.intf.RoleService;
import com.dato.push.app.service.system.intf.SysConfigService;
import com.dato.push.app.service.system.intf.UserService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.dato.push.app.dao.table.Tables.SYS_USER;

/**
 * 权限接口 实现
 * @author sgz
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    @Resource
    private RoleService roleService;
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private UserService userService;
    @Override
    public ResponseResult<NoData> addRole(AddRoleRequest request) {
        SysRole sysRole = BeanUtil.copyProperties(request, SysRole.class);
        sysRole.setCreateTime(new Date());
        sysRole.setStatus(true);
        roleService.getMapper().insert(sysRole);

        return ResponseResult.createSuccess();
    }

    @Override
    public List<SysRole> getRoles() {
        return roleService.getMapper().selectAll();
    }

    @Override
    public void authMenu(AuthMenuRequest request) {
        SysRoleMenu sysRoleMenu = BeanUtil.copyProperties(request, SysRoleMenu.class);
        sysRoleMenuMapper.insert(sysRoleMenu);
    }

    @Override
    public List<SysMenu> getMenus() {
        return sysMenuMapper.selectAll();
    }

    @Override
    public void addMenu(SysMenu request) {
        sysMenuMapper.insert(request);
    }

    @Override
    public void authRole(AuthRoleRequest request) {
        Integer roleId = request.getRoleId();
        String roleKey = roleService.getRoleKey(roleId);
        if (StringUtils.isBlank(roleKey)){
            throw new RuntimeException("未找到对应角色");
        }

        SysUser user = userService.getById(request.getUserId());
        String userRoleKeys = user.getRoleKeys();
        if (StringUtils.isNotBlank(userRoleKeys)){
            // 增加权限
            userRoleKeys = userRoleKeys + "," + roleKey;
        }else {
            userRoleKeys = roleKey;
        }

        user.setRoleKeys(userRoleKeys);
        userService.getMapper().insert(user);
    }

    @Override
    public Page<SysUser> getUsers(UserDetailRequest request) {
        QueryWrapper query = new QueryWrapper();
        query.where(SYS_USER.NAME.likeRight(request.getName()))
                .where(SYS_USER.ACCOUNT.likeRight(request.getAccount()));
        return userService.getMapper().paginate(request.getPageIndex(), request.getPageSize(), query);
    }
}
