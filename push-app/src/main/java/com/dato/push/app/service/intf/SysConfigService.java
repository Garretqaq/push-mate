package com.dato.push.app.service.intf;

import com.dato.push.app.dao.SysMenu;
import com.dato.push.app.dao.SysRole;
import com.dato.push.app.dao.SysUser;
import com.dato.push.app.model.NoData;
import com.dato.push.app.model.ResponseResult;
import com.dato.push.app.model.req.AddRoleRequest;
import com.dato.push.app.model.req.AuthMenuRequest;
import com.dato.push.app.model.req.AuthRoleRequest;
import com.dato.push.app.model.req.UserDetailRequest;
import com.mybatisflex.core.paginate.Page;

import java.util.List;

/**
 * 权限配置 接口
 * @author sgz
 */
public interface SysConfigService {
    ResponseResult<NoData> addRole(AddRoleRequest request);

    /**
     * 获取角色列表
     * @return 角色列表
     */
    List<SysRole> getRoles();

    /**
     * 授权角色 菜单
     * @param request 请求对象
     */
    void authMenu(AuthMenuRequest request);

    /**
     * 获取菜单 列表
     * @return 菜单列表
     */
    List<SysMenu> getMenus();

    /**
     * 增加菜单
     * @param request 请求
     */
    void addMenu(SysMenu request);

    /**
     * 授权用户-角色
     * @param request 授权请求对象
     */
    void authRole(AuthRoleRequest request);

    /**
     * 获取用户 分页详情
     * @param request 请求
     */
    Page<SysUser> getUsers(UserDetailRequest request);
}
