package com.dato.push.app.model.system.req;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 登录请求对象
 * @author sgz
 */
@Getter
@Setter
public class LoginUserRequest {

    /**
     * 账户
     */
    @NotBlank(message = "账号不能为空")
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;
}
