package com.dato.push.app.service.system.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.dato.push.app.dao.SysRole;
import com.dato.push.app.dao.mapper.SysRoleMapper;
import com.dato.push.app.service.system.intf.RoleService;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.dato.push.app.dao.table.Tables.SYS_ROLE;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Override
    public String getRoleKey(Integer id) {
        QueryWrapper query = QueryWrapper.create();
        query.select(SYS_ROLE.ID, SYS_ROLE.ROLE_KEY).where(SYS_ROLE.ID.eq(id));
        return Objects.isNull(sysRoleMapper.selectOneByQuery(query)) ? null : sysRoleMapper.selectOneByQuery(query).getRoleKey();
    }

    @Override
    public List<Integer> getIdByRoleKeys(List<String> roleKeys) {
        if (CollectionUtil.isEmpty(roleKeys)){
            return null;
        }

        QueryWrapper query = QueryWrapper.create();
        query.select(SYS_ROLE.ID).where(SYS_ROLE.ROLE_KEY.in(roleKeys));
        List<SysRole> roleList = sysRoleMapper.selectListByQuery(query);
        if (CollectionUtil.isEmpty(roleList)){
            return null;
        }else {
            return roleList.stream().map(SysRole::getId).collect(Collectors.toList());
        }
    }

    @Override
    public SysRoleMapper getMapper() {
        return this.sysRoleMapper;
    }
}
