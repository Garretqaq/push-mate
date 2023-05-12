package com.dato.push.app.exception;

/**
 * token解析异常
 * @author sgz
 */
public class TokenParseException extends RuntimeException {
    
    public TokenParseException(String message) {
        super(message);
    }
    
}