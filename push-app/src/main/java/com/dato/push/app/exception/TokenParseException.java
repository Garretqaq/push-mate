package com.dato.push.app.exception;

/**
 * token解析异常
 * @author sgz
 */
public class TokenParseException extends RuntimeException {
    
    public TokenParseException(String message) {
        super(message);
    }
    public static TokenParseException fail(){
        return new TokenParseException("Token校验失败");
    }

    public static TokenParseException invalid(){
        return new TokenParseException("Token校验无效");
    }

    public static TokenParseException expired(){
        return new TokenParseException("Token已过期");
    }

}