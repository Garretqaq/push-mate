package com.dato.push.app.model.platform.rsp;

import com.mybatisflex.annotation.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户推送平台配置响应对象
 */
@Getter
@Setter
public class UserPlatFormConfigResponse {
    /**
     * 主键
     */
    private Long id;

    /**
     * 配置名
     */
    private String configName;

    /**
     * 平台
     */
    private String platform;

    /**
     * 创建时间
     */
    private Date createTime;
}
