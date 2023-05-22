package com.dato.push.app.handle;

import com.dato.push.app.model.common.ResponseResult;
import com.dato.push.app.exception.TokenParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import java.util.Objects;

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

    @ExceptionHandler(ServletException.class)
    public ResponseResult<String> handleException(ServletException ex) {
        return ResponseResult.createError(ex.getMessage());
    }


    /**
     * 处理AccessDeniedException无权限异常
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseResult<String> handleException(AccessDeniedException e){
        return ResponseResult.createError("拒绝访问该接口, 请向管理员申请权限");
    }

    /**
     * 处理AuthenticationException无权限异常
     */
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseResult<String> handleException(AuthenticationException e){
        return ResponseResult.createError("身份验证异常, 请向管理员申请");
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResult<String> handleException(MethodArgumentNotValidException e){
        String defaultMessage;

        FieldError fieldError = e.getBindingResult().getFieldError();
        if (Objects.nonNull(fieldError)){
            defaultMessage = fieldError.getDefaultMessage();
        }else {
            defaultMessage = e.getMessage();
        }
        return ResponseResult.createError(defaultMessage);
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult<String> handleException(Exception ex) {
        log.error("接口异常，异常信息", ex);
        return ResponseResult.createError(ex.getMessage());
    }

}