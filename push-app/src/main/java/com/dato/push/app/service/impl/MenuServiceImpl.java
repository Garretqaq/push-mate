package com.dato.push.app.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.dato.push.app.dao.SysMenu;
import com.dato.push.app.dao.SysRoleMenu;
import com.dato.push.app.dao.table.Tables;
import com.dato.push.app.dao.mapper.SysMenuMapper;
import com.dato.push.app.dao.mapper.SysRoleMenuMapper;
import com.dato.push.app.service.intf.MenuService;
import com.dato.push.app.service.intf.RoleService;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static com.dato.push.app.dao.table.Tables.SYS_MENU;
import static com.dato.push.app.dao.table.Tables.SYS_ROLE_MENU;

/**
 * 菜单实现类
 * @author sgz
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Resource
    private RoleService roleService;
    @Override
    public List<SysMenu> getAllMenus() {
        return sysMenuMapper.selectAll();
    }

    @Override
    public List<SysMenu> getMenusByIds(List<Integer> idList) {
        if (CollectionUtil.isEmpty(idList)){
            return null;
        }

        QueryWrapper query = new QueryWrapper();
        query.where(SYS_MENU.ID.in(idList));
        return sysMenuMapper.selectListByQuery(query);
    }

    @Override
    public List<String> getRolesByMenuId(Integer id) {
        QueryWrapper query = new QueryWrapper();
        query.where(Tables.SYS_ROLE_MENU.MENU_ID.eq(id));
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectListByQuery(query);

        return sysRoleMenus.stream().map(item -> {
            String roleKey = roleService.getRoleKey(item.getRoleId());
            return "ROLE_" + roleKey;
        }).collect(Collectors.toList());
    }

    @Override
    public SysMenu getMenuByPath(String path) {
        if (StrUtil.isBlank(path)){
            return null;
        }

        QueryWrapper query = QueryWrapper.create();
        query.where(SYS_MENU.PATH.eq(path));
        return sysMenuMapper.selectOneByQuery(query);
    }

    @Override
    public List<SysMenu> getMenuByRoles(List<String> roleKeys) {
        if (CollectionUtil.isEmpty(roleKeys)){
            return null;
        }

        List<Integer> roleIdList = roleService.getIdByRoleKeys(roleKeys);

        QueryWrapper query = QueryWrapper.create();
        query.where(SYS_ROLE_MENU.ROLE_ID.in(roleIdList));
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuMapper.selectListByQuery(query);
        if (CollectionUtil.isEmpty(sysRoleMenuList)){
            return null;
        }

        List<Integer> menuIdList = sysRoleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        return this.getMenusByIds(menuIdList);
    }
}
