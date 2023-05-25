package com.dato.push.app.model.platform.req;

import lombok.Getter;
import lombok.Setter;

/**
 * 增加平台配置请求对象
 * @since 2023/05/25
 */
@Getter
@Setter
public class AddPlatFormConfigRequest {

    /**
     * 配置名
     */
    private String configName;

    /**
     * 平台
     */
    private String platform;


}
