package com.dato.push.app.global;

import com.dato.push.app.control.ResponseResult;
import com.dato.push.app.exception.TokenParseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常统一处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenParseException.class)
    public ResponseResult<String> handleTokenParseException(TokenParseException ex) {
        return ResponseResult.createError(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<String> handleException(Exception ex) {
        return ResponseResult.createError(ex.getMessage());
    }
}