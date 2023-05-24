package com.dato.push.app.controller;

import com.dato.push.app.dao.SysMenu;
import com.dato.push.app.dao.SysRole;
import com.dato.push.app.dao.SysUser;
import com.dato.push.app.model.common.NoData;
import com.dato.push.app.model.common.ResponseResult;
import com.dato.push.app.model.system.req.AddRoleRequest;
import com.dato.push.app.model.system.req.AuthMenuRequest;
import com.dato.push.app.model.system.req.AuthRoleRequest;
import com.dato.push.app.model.system.req.UserDetailRequest;
import com.dato.push.app.service.system.intf.SysConfigService;
import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 系统配置 控制器
 * @author sgz
 * @since 2023/05/15
 */
@RestController
@RequestMapping("/config")
public class SysConfigController {

    @Resource
    private SysConfigService sysConfigService;

    /**
     * 增加角色
     */
    @PostMapping("/addRole")
    public ResponseResult<NoData> addRole(@RequestBody @Valid AddRoleRequest request){
        return sysConfigService.addRole(request);
    }

    /**
     * 增加菜单
     */
    @PostMapping("/addMenu")
    public ResponseResult<NoData> addMean(@RequestBody SysMenu request){
        sysConfigService.addMenu(request);
        return ResponseResult.createSuccess();
    }


    /**
     * 角色列表
     */
    @GetMapping("/roles")
    public ResponseResult<List<SysRole>> getRoles(){
        List<SysRole> rsp = sysConfigService.getRoles();
        return ResponseResult.createSuccess(rsp);
    }

    /**
     * 授权角色-菜单
     */
    @PostMapping("/auth/menu")
    public ResponseResult<NoData> authMenu(@RequestBody @Valid AuthMenuRequest request){
        sysConfigService.authMenu(request);
        return ResponseResult.createSuccess();
    }

    /**
     * 获取所有菜单
     */
    @GetMapping("/menus")
    public ResponseResult<List<SysMenu>> getMenus(){
        List<SysMenu> rsp = sysConfigService.getMenus();
        return ResponseResult.createSuccess(rsp);
    }

    /**
     * 授权用户-角色
     */
    @PostMapping("/auth/role")
    public ResponseResult<List<SysMenu>> authRole(@RequestBody @Valid AuthRoleRequest request){
        sysConfigService.authRole(request);
        return ResponseResult.createSuccess();
    }

    /**
     * 获取所有用户
     */
    @GetMapping("/users")
    public ResponseResult<Page<SysUser>> getUsers(@RequestBody UserDetailRequest request){
        Page<SysUser> page = sysConfigService.getUsers(request);
        return ResponseResult.createSuccess(page);
    }
}
