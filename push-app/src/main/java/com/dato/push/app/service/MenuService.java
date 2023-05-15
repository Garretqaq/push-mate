package com.dato.push.app.service;

import com.dato.push.app.dao.SysMenu;

import java.util.List;

/**
 * 菜单接口
 * @author sgz
 * @since 2023/05/13
 */
public interface MenuService {
    /**
     * 获取所有菜单
     * @return 菜单
     */
    List<SysMenu> getAllMenus();

    /**
     * 获取 菜单
     * @param idList id
     * @return 菜单
     */
    List<SysMenu> getMenusByIds(List<Integer> idList);

    /**
     * 根据菜单获取对应需要的权限
     * @param id 菜单id
     * @return 角色集合，带前缀ROLE_
     */
    List<String> getRolesByMenuId(Integer id);

    /**
     * 通过path获取菜单
     * @return 菜单
     */
    SysMenu getMenuByPath(String path);

    /**
     * 根据角色获取对应菜单
     * @param roleKeys 角色keys
     * @return 菜单
     */
    List<SysMenu> getMenuByRoles(List<String> roleKeys);
}
