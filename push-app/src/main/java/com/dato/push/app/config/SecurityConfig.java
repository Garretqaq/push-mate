package com.dato.push.app.config;

import com.dato.push.app.service.LoginUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled =true) //开启授权注解功能
public class SecurityConfig {
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Resource
    private LoginUserDetailsService loginUserDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
//                .antMatchers("/user/login", "/static/**").permitAll()
                .antMatchers("/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        //将jwtAuthenticationTokenFilter过滤器放到登录认证之前
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //允许跨域
        http.cors();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(loginUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

}
