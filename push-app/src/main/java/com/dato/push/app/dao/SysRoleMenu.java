package com.dato.push.app.dao;


import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table("sys_role_menu")
public class SysRoleMenu {
    /**
     * 角色ID
     */
    @Column("role_id")
    private Long roleId;

    /**
     * 菜单id
     */
    @Column("menu_id")
    private Long menuId;
}