package com.dato.push.app.global;

import com.dato.push.app.control.ResponseResult;
import com.dato.push.app.exception.TokenParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常统一处理类
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenParseException.class)
    public ResponseResult<String> handleException(TokenParseException ex) {
        return ResponseResult.createError(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseResult<String> handleException(BadCredentialsException ex) {
        return ResponseResult.createError(ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseResult<String> handleException(Exception ex) {
        log.error("接口异常，异常信息", ex);
        return ResponseResult.createError(ex.getMessage());
    }
}