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
@Table("push_platform_config")
public class PushPlatformConfig {
    /**
     * 主键
     */
    @Id
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
     * 用户id
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private Date createTime;
}