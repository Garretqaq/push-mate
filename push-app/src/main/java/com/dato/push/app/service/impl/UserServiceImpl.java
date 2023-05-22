package com.dato.push.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.dato.push.app.dao.SysMenu;
import com.dato.push.app.dao.SysUser;
import com.dato.push.app.dao.mapper.SysUserMapper;
import com.dato.push.app.model.common.LoginUser;
import com.dato.push.app.model.system.rep.UserInfoResponse;
import com.dato.push.app.model.system.req.UpdatePasswordRequest;
import com.dato.push.app.service.intf.MenuService;
import com.dato.push.app.service.intf.UserService;
import com.dato.push.app.utils.CommonUtil;
import com.dato.push.app.utils.LRUCacheUtil;
import com.dato.push.app.utils.UserContextUtil;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
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

    @Resource
    private MenuService menuService;

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
    public List<SysMenu> authority() {
        LoginUser user = UserContextUtil.getCurrentUser();
        String roleKeys = user.getRoleKeys();
        if (StringUtils.isBlank(roleKeys)){
            return null;
        }

        // 菜单列表
        return menuService.getMenuByRoles(StrUtil.split(roleKeys, ","));

    }

    @Override
    public SysUser getById(Integer id) {
        QueryWrapper query = QueryWrapper.create();
        query.where(SYS_USER.ID.eq(id));
        return sysUserMapper.selectOneByQuery(query);
    }

    @Override
    public SysUserMapper getMapper() {
        return this.sysUserMapper;
    }

    @Override
    public void updatePassword(UpdatePasswordRequest request) {
        Integer userId = request.getUserId();

        PasswordEncoder passwordEncoder = SpringUtil.getBean("passwordEncoder");
        LoginUser currentUser = UserContextUtil.getCurrentUser();
        // 不是本人 而且也不是 管理员
        if (!currentUser.getId().equals(userId) && !UserContextUtil.whetherAdmin(currentUser)){
            throw new AccessDeniedException("拒绝访问该接口, 请向管理员申请权限");
        }

        SysUser sysUser = BeanUtil.copyProperties(currentUser, SysUser.class);

        if (!passwordEncoder.matches(request.getPassword(), sysUser.getPassword())){
            // 不匹配
            throw new RuntimeException("原密码输入不正确，请重新输入");
        }

        if (!CommonUtil.validatePassword(request.getPassword())){
            throw new RuntimeException("密码请以字母开头，并且大于6位");
        }

        sysUser.setPassword(passwordEncoder.encode(request.getPassword()));
        // 清除缓存
        LRUCacheUtil.removeUser(userId);
    }

    @Override
    public UserInfoResponse getUserInfo() {
        LoginUser currentUser = UserContextUtil.getCurrentUser();

        UserInfoResponse rsp = new UserInfoResponse();
        rsp.setName(currentUser.getName());
        rsp.setAccount(currentUser.getAccount());
        return rsp;
    }
}
