package com.dato.push.app.service.impl;

import com.dato.push.app.dao.table.Tables;
import com.dato.push.app.mapper.SysRoleMapper;
import com.dato.push.app.service.RoleService;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Override
    public String getRoleKey(Integer id) {
        QueryWrapper query = QueryWrapper.create();
        query.where(Tables.SYS_ROLE.ID.eq(id));
        return Objects.isNull(sysRoleMapper.selectOneByQuery(query)) ? null : sysRoleMapper.selectOneByQuery(query).getRoleKey();
    }
}
