package com.dato.push.app.service.platform.intf;

import com.dato.push.app.dao.PushPlatform;
import com.dato.push.app.model.common.PageCommonResponse;
import com.dato.push.app.model.platform.req.UserPlatFormConfigRequest;
import com.dato.push.app.model.platform.rsp.UserPlatFormConfigResponse;

import java.util.List;

/**
 * 推送平台 服务类
 * @author sgz
 */
public interface PushPlatformService {
    /**
     * 获取所有可推送平台
     * @return 平台
     */
    List<PushPlatform> list();

    /**
     * 获取用户配置的推送配置
     *
     * @param request 请求对象
     * @return 推送配置
     */
    PageCommonResponse<UserPlatFormConfigResponse> userConfig(UserPlatFormConfigRequest request);

    /**
     * 删除用户配置的推送配置
     * @param id id
     */
    void userDelete(Long id);
}
