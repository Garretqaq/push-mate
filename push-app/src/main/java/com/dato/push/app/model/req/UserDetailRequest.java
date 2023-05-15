package com.dato.push.app.model.req;

import com.dato.push.app.model.PageCommonRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailRequest extends PageCommonRequest {

    /**
     * 用户名
     */
    private String name;

    /**
     * 账户
     */
    private String account;
}
