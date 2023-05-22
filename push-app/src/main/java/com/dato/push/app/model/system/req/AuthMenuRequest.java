package com.dato.push.app.model.system.req;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 授权菜单请求对象
 * @author sgz
 */
@Getter
@Setter
public class AuthMenuRequest {

    /**
     * 角色id
     */
    @NotNull
    private Integer roleId;

    /**
     * 菜单id
     */
    @NotNull
    private Integer menuId;
}
