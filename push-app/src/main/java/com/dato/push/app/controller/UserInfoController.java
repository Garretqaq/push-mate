package com.dato.push.app.controller;

import com.dato.push.app.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户信息 控制器
 */
@RestController
@RequestMapping("/user/info")
public class UserInfoController {
    @Resource
    private UserService userService;

    /**
     * 登录接口
     */
    @PostMapping("/authority")
    public ResponseResult<String> permissions(){
        userService.authority();
        return null;
    }

}
