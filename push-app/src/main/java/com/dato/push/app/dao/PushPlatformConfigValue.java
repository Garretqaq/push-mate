package com.dato.push.app.dao;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "push_platform_config_value")
public class PushPlatformConfigValue {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 更新时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 所属配置id
     */
    @Column(name = "config_id")
    private Long configId;

    /**
     * 参数键
     */
    private String key;

    /**
     * 参数值
     */
    private String value;

    /**
     * 创建人
     */
    @Column(name = "user_id")
    private Integer userId;
}