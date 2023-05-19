package com.dato.push.app.user;

import com.dato.push.app.PushAppApplicationTests;
import com.dato.push.app.dao.mapper.SysUserMapper;
import com.dato.push.app.dao.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class UserTest extends PushAppApplicationTests {

    @Resource
    private SysUserMapper sysUserMapper;
    @Test
    public void test1(){
        SysUser sysUser = new SysUser();
        sysUser.setName("测试");
        sysUser.setAccount("admin");
        sysUser.setPassword("123");
        sysUserMapper.insert(sysUser);
    }
}
