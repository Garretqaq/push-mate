package com.dato.push.app.service.intf;

import com.dato.push.app.model.NoData;
import com.dato.push.app.model.ResponseResult;
import com.dato.push.app.model.req.LoginUserRequest;
import com.dato.push.app.model.req.RegisterRequest;

/**
 * 登录接口
 * @since 2023-05-12
 * @author sgz
 */
public interface LoginService {

    /**
     * 登录
     * @param request 请求对象
     * @return 结果
     */
    ResponseResult<String> login(LoginUserRequest request);

    /**
     * 登出
     * @return 结果
     */
    ResponseResult<NoData> loginOut();

    /**
     * 注册
     * @param request 请求对象
     * @return 注册结果
     */
    ResponseResult<NoData> register(RegisterRequest request);
}
