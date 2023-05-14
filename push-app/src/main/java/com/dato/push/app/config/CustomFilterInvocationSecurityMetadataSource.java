package com.dato.push.app.config;

import cn.hutool.core.util.StrUtil;
import com.dato.push.app.dao.SysMenu;
import com.dato.push.app.service.MenuService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 *  URL 路径和权限之间的关系配置类
 * @author sgz
 * @since 2023/05/13
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Resource
    private MenuService menuService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        SysMenu menu = menuService.getMenuByPath(requestUrl);

        if (Objects.isNull(menu)){
            return null;
        }

        List<String> permAndRole = new ArrayList<>();

        // 菜单所需要的权限
        String perms = menu.getPerms();
        if (StrUtil.isNotBlank(perms)){
            permAndRole.addAll(StrUtil.split(perms, ","));
        }

        return SecurityConfig.createList(permAndRole.toArray(new String[0]));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
