package com.dato.push.app.service.intf;


import com.dato.push.app.dao.mapper.SysRoleMapper;

import java.util.List;

/**
 * 角色接口
 * @author sgz
 */
public interface RoleService {

    /**
     * 获取角色的 标识
     * @param id id
     * @return key
     */
    String getRoleKey(Integer id);

    /**
     * 根据 标识 获取roleId
     * @param roleKeys 角色标识
     * @return roleId
     */
    List<Integer> getIdByRoleKeys(List<String> roleKeys);

    /**
     * 获取mapper对象
     * @return mapper
     */
    SysRoleMapper getMapper();
}
