package com.dato.push.app.model.rep;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户信息返回
 */
@Getter
@Setter
public class UserInfoResponse {
    /**
     * 名字
     */
    private String name;

    /**
     * 账户
     */
    private String account;
}
