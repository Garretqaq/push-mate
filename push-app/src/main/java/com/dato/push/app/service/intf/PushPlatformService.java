package com.dato.push.app.service.intf;

import com.dato.push.app.dao.PushPlatform;

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
}
