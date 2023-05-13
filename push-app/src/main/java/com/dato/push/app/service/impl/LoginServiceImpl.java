package com.dato.push.app.service.impl;

import com.dato.push.app.control.ResponseResult;
import com.dato.push.app.dao.SysUser;
import com.dato.push.app.dao.table.Tables;
import com.dato.push.app.mapper.SysUserMapper;
import com.dato.push.app.model.LoginUser;
import com.dato.push.app.model.req.LoginUserRequest;
import com.dato.push.app.service.LoginService;
import com.dato.push.app.service.RoleService;
import com.dato.push.app.utils.JwtTokenUtil;
import com.dato.push.app.utils.LRUCacheUtil;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RoleService roleService;

    @Override
    public ResponseResult<String> login(LoginUserRequest request) {
        //通过UsernamePasswordAuthenticationToken获取用户名和密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getAccount(), request.getPassword());

        //AuthenticationManager委托机制对authenticationToken 进行用户认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //如果认证没有通过，给出对应的提示
        if (Objects.isNull(authenticate)){
            return ResponseResult.createError("账号密码错误");
        }

        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        // 缓存
        LRUCacheUtil.cache(loginUser);

        //如果认证通过，使用user生成jwt  jwt存入ResponseResult 返回
        String token = JwtTokenUtil.createToken(loginUser);
        return ResponseResult.createSuccess(token);
    }

    @Override
    public ResponseResult<?> loginOut() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();

        LRUCacheUtil.removeUser(loginUser.getId());
        return ResponseResult.createSuccess();
    }

    @Override
    public ResponseResult<?> register(LoginUserRequest request) {
        String account = request.getAccount();
        String password = request.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return ResponseResult.createError("请传入的账号或密码不为空");
        }

        QueryWrapper queryWrapper = QueryWrapper.create();
        queryWrapper.where(Tables.SYS_USER.ACCOUNT.eq(account));
        SysUser sysUser = sysUserMapper.selectOneByQuery(queryWrapper);

        if (!Objects.isNull(sysUser)){
            return ResponseResult.createError("已经有相同账号，请更换用户名");
        }

        sysUser = new SysUser();
        sysUser.setAccount(request.getAccount());
        sysUser.setName(request.getName());
        sysUser.setRoleKeys(roleService.getRoleKey(1));
        sysUser.setPassword(passwordEncoder.encode(request.getPassword()));

        sysUserMapper.insert(sysUser);
        return ResponseResult.createSuccess();
    }
}
