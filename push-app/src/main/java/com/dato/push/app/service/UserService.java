package com.dato.push.app.service;

import com.dato.push.app.dao.SysUser;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    void authority();
}
