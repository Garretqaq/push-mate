package com.dato.push.app.control;

import com.dato.push.app.model.req.LoginUserRequest;
import com.dato.push.app.service.LoginService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController()
@RequestMapping("/user")
public class LoginUserController {

    @Resource
    private LoginService loginService;

    /**
     * 登录接口
     */
    @PostMapping("login")
    public ResponseResult<String> login(LoginUserRequest request){
        return loginService.login(request);
    }

    /**
     * 登出接口
     */
    @GetMapping("loginOut")
    public ResponseResult<?> register(){
        return loginService.loginOut();
    }

    /**
     * 注册接口
     */
    @PreAuthorize("hasAnyAuthority('admin')")
    @PostMapping("register")
    public ResponseResult<?> loginOut(LoginUserRequest request){
        return loginService.register(request);
    }
}
