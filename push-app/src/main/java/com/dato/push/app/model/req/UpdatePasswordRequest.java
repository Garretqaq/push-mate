package com.dato.push.app.model.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 更改密码请求对象
 * @author sgz
 */
@Getter
@Setter
public class UpdatePasswordRequest {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Integer userId;

    /**
     * 用户原密码
     */
    @NotBlank(message = "原密码为空")
    private String oldPassword;

    /**
     * 密码
     */
    @NotBlank(message = "新密码")
    private String password;
}
