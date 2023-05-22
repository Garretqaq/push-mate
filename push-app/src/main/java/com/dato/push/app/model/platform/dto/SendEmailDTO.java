package com.dato.push.app.model.platform.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 发送邮箱传输对象
 * @author sgz
 */
@Getter
@Setter
public class SendEmailDTO {

    /**
     * 账户名
     */
    private String user;

    /**
     * 来自 | 示例：xxx<1111111@qq.com>
     */
    private String from;

    /**
     * 服务地址
     */
    private String host;

    /**
     * 服务端口
     */
    private String port;


}
