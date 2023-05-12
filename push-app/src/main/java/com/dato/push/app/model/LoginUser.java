package com.dato.push.app.model;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录验证实体类
 * @author sgz
 */
@Getter
@Setter
public class LoginUser implements UserDetails {
    /**
     * 用户id
     */
    private Integer id;

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
     * 用户角色key | 多个以逗号分割
     */
    private String roleKeys;

    /**
     * 权限集合
     */
    private List<String> permission;

    /**
     * 是否开启 | false:未开启 true:开启
     */
    private Boolean enable;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> authorities;
        // 角色
        List<String> roleList = StrUtil.split(roleKeys, ",");
        authorities = roleList.stream().map(item -> "ROLE_" + item).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(permission)){
            authorities.addAll(permission);
        }
        return AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
