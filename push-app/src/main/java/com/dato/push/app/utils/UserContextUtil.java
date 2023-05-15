package com.dato.push.app.utils;

import com.dato.push.app.model.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
}
