package com.dato.push.app.controller;

import com.dato.push.app.dao.SysMenu;
import com.dato.push.app.dao.SysUser;
import com.dato.push.app.model.ResponseResult;
import com.dato.push.app.model.rep.UserInfoResponse;
import com.dato.push.app.model.req.UpdatePasswordRequest;
import com.dato.push.app.service.intf.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户信息 控制器
 */
@RestController
@RequestMapping("/user/info")
public class UserInfoController {
    @Resource
    private UserService userService;

    /**
     * 获取当前用户信息
     */
    @GetMapping
    public ResponseResult<UserInfoResponse> getInfo(){
        UserInfoResponse userInfo = userService.getUserInfo();
        return ResponseResult.createSuccess(userInfo);
    }

    /**
     * 用户所有权限
     */
    @PostMapping("/authority")
    public ResponseResult<List<SysMenu>> authority(){
        return ResponseResult.createSuccess(userService.authority());
    }

    /**
     * 用户更改密码
     */
    @PostMapping("/update/password")
    public ResponseResult<List<SysMenu>> updatePassword(@RequestBody @Valid UpdatePasswordRequest request){
        userService.updatePassword(request);
        return ResponseResult.createSuccess();
    }

}
