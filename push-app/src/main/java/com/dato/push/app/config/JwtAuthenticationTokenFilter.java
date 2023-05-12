package com.dato.push.app.config;

import com.dato.push.app.model.LoginUser;
import com.dato.push.app.utils.JwtTokenUtil;
import com.dato.push.app.utils.LRUCacheUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }
        // 校验token
        JwtTokenUtil.verify(token);

        // 解析token
        Integer userId = JwtTokenUtil.parseToken(token);
        // 获取用户信息
        LoginUser loginUser = LRUCacheUtil.getLoginUser(userId);

        //封装Authentication对象存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken =
               new UsernamePasswordAuthenticationToken(loginUser,null,null);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //放行
        filterChain.doFilter(request, response);
    }
}
