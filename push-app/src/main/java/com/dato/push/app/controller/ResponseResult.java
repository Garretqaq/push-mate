package com.dato.push.app.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ResponseResult<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 查询到的结果数据，
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data;

    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;


    public Integer getCode() {
        return code;
    }

    /**
     * 成功返回值
     * @param data 返回数据
     * @param <T>  泛型
     * @return 返回值
     */
    public static <T> ResponseResult<T> createSuccess(T data) {
        return new ResponseResult<>(200, data, "请求成功");
    }

    /**
     * 创建无内容的成功返回值
     * @return ResultVO<NoDataVo>
     */
    public static <T> ResponseResult<T> createSuccess() {
        return new ResponseResult<>(200, null, "请求成功");
    }

    /**
     * 创建成功的返回值
     * @param data 数据
     * @param message 消息
     * @return ResultVO<T>
     * @param <T> 泛型
     */
    public static <T> ResponseResult<T> createSuccess(T data, String message) {
        return new ResponseResult<>(200, data, message);
    }

    /**
     * 错误返回值-自定义消息
     * @param message 返回消息
     * @return 返回值
     */
    public static <T> ResponseResult<T> createError(String message) {
        return new ResponseResult<>(400, null, message);
    }

    /**
     * 异常返回值-自定义消息
     *
     * @return 返回值
     */
    public static ResponseResult<?> createError() {
        return new ResponseResult<>(400, null, "请求失败");
    }

}
