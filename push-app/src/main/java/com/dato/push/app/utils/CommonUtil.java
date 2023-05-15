package com.dato.push.app.utils;

/**
 * 通用工具类
 * @author sgz
 */
public class CommonUtil {


    /**
     * 判断密码是否以字母开头，并大于六位
     * @param password 密码
     * @return 是否合法
     */
    public static boolean validatePassword(String password) {
        if (password.length() <= 6) {
            return false;
        }
        return Character.isLetter(password.charAt(0));
    }

}
