package com.dato.push.app.service.impl;

import cn.hutool.core.util.StrUtil;
import com.dato.push.app.dao.SysMenu;
import com.dato.push.app.dao.SysRoleMenu;
import com.dato.push.app.dao.table.Tables;
import com.dato.push.app.mapper.SysMenuMapper;
import com.dato.push.app.mapper.SysRoleMenuMapper;
import com.dato.push.app.service.MenuService;
import com.dato.push.app.service.RoleService;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static com.dato.push.app.dao.table.Tables.SYS_MENU;

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
}
