package com.dato.push.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")   // 设置要处理的请求
                .allowedOrigins("*")   // 允许所有来源的跨域请求
                .allowedMethods("GET","POST", "PUT","DELETE", "PATCH")   // 允许请求
                .maxAge(1800);   // 设置缓存时间，减少跨域请求次数
    }
}