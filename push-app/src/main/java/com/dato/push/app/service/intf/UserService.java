package com.dato.push.app.service.intf;

import com.dato.push.app.dao.SysMenu;
import com.dato.push.app.dao.SysUser;
import com.dato.push.app.dao.mapper.SysUserMapper;
import com.dato.push.app.model.req.UpdatePasswordRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * 用户接口
 */
public interface UserService {
    /**
     * 初始化管理员
     *
     * @param account         账户
     * @param password        密码
     * @param passwordEncoder 密码加密器
     */
    void initAdmin(String account, String password, PasswordEncoder passwordEncoder);

    /**
     * 根据用户名获取用户实体
     * @param account 用户名
     * @return 用户实体
     */
    SysUser getUserByAccount(String account);

    /**
     * 获取用户菜单权限
     * @return 权限列表
     */
    List<SysMenu> authority();

    /**
     * 获取用户
     * @param id id
     * @return 用户
     */
    SysUser getById(Integer id);

    SysUserMapper getMapper();

    /**
     * 修改密码
     * @param request 请求对象
     */
    void updatePassword(UpdatePasswordRequest request);
}
