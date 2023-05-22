package com.dato.push.app.model.system.req;

import com.dato.push.app.model.common.AbstractPageRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailRequest extends AbstractPageRequest {

    /**
     * 用户名
     */
    private String name;

    /**
     * 账户
     */
    private String account;
}
