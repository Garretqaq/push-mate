package com.dato.push.app.control;

import com.dato.push.app.model.req.LoginUserRequest;
import com.dato.push.app.service.LoginService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public ResponseResult<String> login(@RequestBody LoginUserRequest request){
        return loginService.login(request);
    }

    /**
     * 登出接口
     */
    @GetMapping("/loginOut")
    public ResponseResult<?> register(){
        return loginService.loginOut();
    }

    /**
     * 注册接口
     */
    @PreAuthorize("hasAnyRole('admin')")
    @PostMapping("/register")
    public ResponseResult<?> loginOut(@RequestBody LoginUserRequest request){
        return loginService.register(request);
    }

    /**
     * 测试接口
     */
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/test")
    public ResponseResult<?> test(){
        return ResponseResult.createSuccess();
    }
}
