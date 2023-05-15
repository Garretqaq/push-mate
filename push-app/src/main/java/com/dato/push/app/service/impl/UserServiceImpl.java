package com.dato.push.app.service.impl;

import com.dato.push.app.dao.SysUser;
import com.dato.push.app.mapper.SysUserMapper;
import com.dato.push.app.service.UserService;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

import static com.dato.push.app.dao.table.Tables.SYS_USER;

/**
 * 用户表实现类
 * @author sgz
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public void initAdmin(String account, String password, PasswordEncoder passwordEncoder) {
        QueryWrapper query = QueryWrapper.create();
        query.where(SYS_USER.ACCOUNT.eq(account));
        SysUser sysUser = sysUserMapper.selectOneByQuery(query);

        if (Objects.isNull(sysUser)){
            // 插入
            sysUser = new SysUser();
            sysUser.setAccount(account);
            sysUser.setPassword(passwordEncoder.encode(password));
            sysUser.setRoleKeys("admin");
            sysUser.setCreatTime(new Date());
            sysUserMapper.insert(sysUser);
        }else {
            // 更新
            sysUser.setAccount(account);
            sysUser.setPassword(passwordEncoder.encode(password));
            sysUser.setRoleKeys("admin");
            sysUserMapper.updateByQuery(sysUser, query);
        }
    }

    @Override
    public SysUser getUserByAccount(String account) {
        QueryWrapper query = QueryWrapper.create();
        query.where(SYS_USER.ACCOUNT.eq(account));
        return sysUserMapper.selectOneByQuery(query);
    }

    @Override
    public void authority() {

    }
}
