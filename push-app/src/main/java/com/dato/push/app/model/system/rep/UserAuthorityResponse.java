package com.dato.push.app.model.system.rep;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户权限返回对象
 *
 * @since 2023/05/14
 * @author sgz
 */
@Getter
@Setter
@NoArgsConstructor
public class UserAuthorityResponse {
    /**
     * 用户名
     */
    private String name;
    /**
     * 可以访问路径
     */
    private String url;

    /**
     * 权限编码
     */
    private String permission;

    /**
     * 图片url
     */
    private String link;

}
