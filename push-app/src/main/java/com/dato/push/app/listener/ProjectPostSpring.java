package com.dato.push.app.listener;

import com.dato.push.app.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 项目启动初始化
 *
 * @author sgz
 * @since 2023/5/12
 **/
@Component
@Slf4j
public class ProjectPostSpring implements ApplicationContextAware {

    @Value(("${admin.account}"))
    private String account;

    @Value("${admin.password}")
    private String password;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 初始化管理员账号密码
        sysUserService.initAdmin(account, password, passwordEncoder);
        log.info("初始化管理员密码完成======账号：{},密码：{}", account, password);
    }
}
