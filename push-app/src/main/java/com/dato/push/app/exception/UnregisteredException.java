package com.dato.push.app.exception;

/**
 * 未注册异常
 * @author sgz
 */
public class UnregisteredException extends RuntimeException{

    public UnregisteredException(String message) {
        super(message);
    }

    public static UnregisteredException noRegister(){
        return new UnregisteredException("抱歉，请注册用户再进行登录");
    }
}
