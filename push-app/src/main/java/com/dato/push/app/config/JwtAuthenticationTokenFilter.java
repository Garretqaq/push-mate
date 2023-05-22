package com.dato.push.app.config;

import cn.hutool.extra.spring.SpringUtil;
import com.dato.push.app.exception.TokenParseException;
import com.dato.push.app.model.common.LoginUser;
import com.dato.push.app.utils.JwtTokenUtil;
import com.dato.push.app.utils.LRUCacheUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value(("${admin.account}"))
    private String account;

    @Value("${admin.password}")
    private String password;

    @Resource
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("Authorization");
        if (!StringUtils.hasText(token)) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }

        // 校验token
        try {
            JwtTokenUtil.verify(token);
        } catch (TokenParseException e) {
            handlerExceptionResolver.resolveException(request, response,
                    null, e);
            return;
        } catch (Exception e){
            handlerExceptionResolver.resolveException(request, response,
                    null, TokenParseException.fail());
            return;
        }

        // 解析token
        Integer userId = JwtTokenUtil.parseToken(token);

        // 获取用户信息
        LoginUser loginUser = LRUCacheUtil.getLoginUser(userId);

        if (Objects.nonNull(loginUser)){
            //封装Authentication对象存入SecurityContextHolder
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }else if (userId.equals(1)){
            // 管理员缓存
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(account, password);

            // 验证并存入缓存
            AuthenticationManager authenticationManager = SpringUtil.getBean(AuthenticationManager.class);
            Authentication authenticate = authenticationManager.authenticate(authentication);

            LRUCacheUtil.cache((LoginUser) authenticate.getPrincipal());
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        }

        //放行
        filterChain.doFilter(request, response);
    }
}
