package com.dato.push.app.service;

import com.dato.push.app.dao.SysUser;

/**
 * 用户接口
 */
public interface SysUserService {
    /**
     * 初始化管理员
     * @param account 账户
     * @param password 密码
     */
    void initAdmin(String account, String password);

    /**
     * 根据用户名获取用户实体
     * @param account 用户名
     * @return 用户实体
     */
    SysUser getUserByAccount(String account);
}
