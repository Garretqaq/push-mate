package com.dato.push.app.model;

import java.util.Date;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "sys_role")
public class SysRole {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String name;

    /**
     * 角色权限字符串
     */
    @Column(name = "role_key")
    private String roleKey;

    /**
     * 角色状态（0停用 1正常）
     */
    private Boolean status;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 备注
     */
    private String remark;
}