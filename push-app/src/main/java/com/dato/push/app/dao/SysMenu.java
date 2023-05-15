package com.dato.push.app.dao;

import java.util.Date;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table("sys_menu")
public class SysMenu {
    @Id
    private Integer id;

    /**
     * 菜单名
     */
    @Column("menu_name")
    private String menuName;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 菜单状态（false停用 true正常）
     */
    private Boolean status;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 创建时间
     */
    @Column("create_time")
    private Date createTime;

    /**
     * 备注
     */
    private String remark;
}