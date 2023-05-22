package com.dato.push.app.service.impl;

import com.dato.push.app.dao.PushPlatform;
import com.dato.push.app.dao.mapper.PushPlatformMapper;
import com.dato.push.app.service.intf.PushPlatformService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 推送平台 实现类
 * @author sgz
 */
@Service
public class PushPlatformServiceImpl implements PushPlatformService {

    @Resource
    private PushPlatformMapper pushPlatformMapper;
    @Override
    public List<PushPlatform> list() {
        return pushPlatformMapper.selectAll();
    }
}
