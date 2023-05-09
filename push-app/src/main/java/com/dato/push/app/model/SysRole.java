package com.dato.push.app.model;

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
@Table("sys_role")
public class SysRole {
    @Id
    private Long id;

    private String name;

    /**
     * 角色权限字符串
     */
    @Column("role_key")
    private String roleKey;

    /**
     * 角色状态（0停用 1正常）
     */
    private Boolean status;

    @Column("create_time")
    private Date createTime;

    /**
     * 备注
     */
    private String remark;
}