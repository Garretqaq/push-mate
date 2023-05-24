package com.dato.push.app.model.platform.req;

import com.dato.push.app.model.common.AbstractPageRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * 用户平台配置请求对象
 * @author  sgz
 * @since  2023/05/24
 */
@Getter
@Setter
public class UserPlatFormConfigRequest extends AbstractPageRequest {
    /**
     * 用户id
     */
    @NotNull
    private Integer userId;

    /**
     * 平台
     */
    private String platform;

}
