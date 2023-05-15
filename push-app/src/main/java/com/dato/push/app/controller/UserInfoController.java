package com.dato.push.app.controller;

import com.dato.push.app.dao.SysMenu;
import com.dato.push.app.model.LoginUser;
import com.dato.push.app.model.ResponseResult;
import com.dato.push.app.model.rep.UserAuthorityResponse;
import com.dato.push.app.service.RoleService;
import com.dato.push.app.service.UserService;
import com.dato.push.app.utils.UserContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息 控制器
 */
@RestController
@RequestMapping("/user/info")
public class UserInfoController {
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    /**
     * 用户所有权限
     */
    @PostMapping("/authority")
    public ResponseResult<List<SysMenu>> authority(){
        return ResponseResult.createSuccess(userService.authority());
    }

}
