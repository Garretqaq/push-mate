package com.dato.push.app.model.system.req;

import lombok.Getter;
import lombok.Setter;

/**
 * 授权用户 角色请求对象
 * @author sgz
 */
@Getter
@Setter
public class AuthRoleRequest {
    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 用户id
     */
    private Integer userId;
}
