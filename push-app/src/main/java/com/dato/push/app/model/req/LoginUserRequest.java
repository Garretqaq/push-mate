package com.dato.push.app.model.req;


import lombok.Getter;
import lombok.Setter;

/**
 * 登录请求对象
 * @author sgz
 */
@Getter
@Setter
public class LoginUserRequest {
    /**
     * 名称
     */
    private String name;

    /**
     * 账户
     */
    private String account;

    /**
     * 密码
     */
    private String password;
}
