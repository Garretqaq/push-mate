package com.dato.push.app.model;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 * @author sgz
 */
@Table("sys_user")
@Getter
@Setter
@NoArgsConstructor
public class SysUser implements Serializable {
    /**
     * id
     */
    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 账户
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private Date creatTime;
}
