package com.dato.push.app.dao;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table("push_platform")
public class PushPlatform {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 关键词
     */
    private String key;

    /**
     * 名字
     */
    private String name;
}