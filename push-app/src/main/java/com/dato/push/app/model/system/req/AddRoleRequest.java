package com.dato.push.app.model.system.req;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 增加权限请求
 */
@Getter
@Setter
public class AddRoleRequest {
    /**
     * 权限名
     */
    @NotBlank
    private String name;

    /**
     * 角色权限字符串
     */
    @NotBlank
    private String roleKey;
}
