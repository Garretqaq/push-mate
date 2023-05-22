package com.dato.push.app.utils;

import com.dato.push.app.model.common.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * 用户上下文信息
 * @author sgz
 * @since 2023/05/15
 */
public class UserContextUtil {

    /**
     * 获取当前用户信息
     * @return 用户信息
     */
    public static LoginUser getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (LoginUser) authentication.getPrincipal();
    }

    /**
     * 判断用户是否为管理员
     * @return 用户信息
     */
    public static Boolean whetherAdmin(LoginUser user){
        boolean admin = false;

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String auth = authority.getAuthority();
            // 判断是否有该角色
            if (auth.equals("ROLE_admin")){
                admin = true;
                break;
            }
        }
        return admin;
    }
}
