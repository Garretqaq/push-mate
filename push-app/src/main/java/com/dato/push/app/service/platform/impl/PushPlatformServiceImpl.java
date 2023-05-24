package com.dato.push.app.service.platform.impl;

import cn.hutool.core.bean.BeanUtil;
import com.dato.push.app.dao.PushPlatform;
import com.dato.push.app.dao.PushPlatformConfig;
import com.dato.push.app.dao.mapper.PushPlatformConfigMapper;
import com.dato.push.app.dao.mapper.PushPlatformMapper;
import com.dato.push.app.dao.table.Tables;
import com.dato.push.app.model.common.PageCommonResponse;
import com.dato.push.app.model.platform.req.UserPlatFormConfigRequest;
import com.dato.push.app.model.platform.rsp.UserPlatFormConfigResponse;
import com.dato.push.app.service.platform.intf.PushPlatformService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
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

    @Resource
    private PushPlatformConfigMapper pushPlatformConfigMapper;
    @Override
    public List<PushPlatform> list() {
        return pushPlatformMapper.selectAll();
    }

    @Override
    public PageCommonResponse<UserPlatFormConfigResponse> userConfig(UserPlatFormConfigRequest request) {
        QueryWrapper query = QueryWrapper.create();
        query.where(Tables.PUSH_PLATFORM_CONFIG.USER_ID.eq(request.getUserId()));
        if (StringUtils.isNotBlank(request.getPlatform())){
            query.and(Tables.PUSH_PLATFORM_CONFIG.PLATFORM.eq(request.getPlatform()));
        }

        // 结果
        Page<PushPlatformConfig> configPage = pushPlatformConfigMapper.paginate(request.getPageIndex(), request.getPageSize(), query);
        return PageCommonResponse.covert(configPage, item -> BeanUtil.copyProperties(item, UserPlatFormConfigResponse.class));
    }

    @Override
    public void userDelete(Long id) {
        pushPlatformConfigMapper.deleteById(id);
    }
}
