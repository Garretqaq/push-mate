package com.dato.push.app.dao;

import java.util.Date;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table("push_platform_config_value")
public class PushPlatformConfigValue {
    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 创建时间
     */
    private Date updateTime;

    /**
     * 更新时间
     */
    private Date createTime;

    /**
     * 所属配置id
     */
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
    private Integer userId;
}