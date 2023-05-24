package com.dato.push.app.controller;

import com.dato.push.app.model.common.NoData;
import com.dato.push.app.model.common.ResponseResult;
import com.dato.push.app.model.system.req.LoginUserRequest;
import com.dato.push.app.model.system.req.RegisterRequest;
import com.dato.push.app.service.system.intf.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 登录控制层
 */
@RestController
@RequestMapping("/user")
public class LoginUserController {

    @Resource
    private LoginService loginService;

    /**
     * 登录接口
     */
    @PostMapping("/login")
    public ResponseResult<String> login(@RequestBody @Valid LoginUserRequest request){
        return loginService.login(request);
    }

    /**
     * 登出接口
     */
    @GetMapping("/loginOut")
    public ResponseResult<NoData> loginOut(){
        return loginService.loginOut();
    }

    /**
     * 注册接口
     */
    @PostMapping("/register")
    public ResponseResult<NoData> register(@RequestBody @Valid RegisterRequest request){
        return loginService.register(request);
    }

    /**
     * 测试接口
     */
    @GetMapping("/test")
    public ResponseResult<NoData> test(){
        return ResponseResult.createSuccess();
    }
}
