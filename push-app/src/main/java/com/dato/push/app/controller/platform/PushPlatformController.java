package com.dato.push.app.controller.platform;

import com.dato.push.app.dao.PushPlatform;
import com.dato.push.app.model.common.NoData;
import com.dato.push.app.model.common.PageCommonResponse;
import com.dato.push.app.model.common.ResponseResult;
import com.dato.push.app.model.platform.req.UserPlatFormConfigRequest;
import com.dato.push.app.model.platform.rsp.UserPlatFormConfigResponse;
import com.dato.push.app.service.platform.intf.PushPlatformService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 推送管理 控制器
 */
@RestController
@RequestMapping("/platform")
public class PushPlatformController {
    @Resource
    private PushPlatformService pushPlatformService;

    /**
     * 获取所有可推送平台
     */
    @GetMapping("/list")
    public ResponseResult<List<PushPlatform>> list(){
        List<PushPlatform> rspList = pushPlatformService.list();
        return ResponseResult.createSuccess(rspList);
    }

    /**
     * 获取用户配置的推送配置
     */
    @PostMapping("/user/list")
    public ResponseResult<PageCommonResponse<UserPlatFormConfigResponse>> userConfig(@RequestBody @Validated UserPlatFormConfigRequest request){
        PageCommonResponse<UserPlatFormConfigResponse> rspPage = pushPlatformService.userConfig(request);
        return ResponseResult.createSuccess(rspPage);
    }

    /**
     * 删除用户配置的推送配置
     */
    @DeleteMapping("/user/delete")
    public ResponseResult<NoData> userDelete(@RequestParam Long id){
        pushPlatformService.userDelete(id);
        return ResponseResult.createSuccess();
    }
}
