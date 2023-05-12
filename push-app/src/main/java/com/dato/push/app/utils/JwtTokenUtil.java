package com.dato.push.app.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.KeyUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.AlgorithmUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.dato.push.app.exception.TokenParseException;
import com.dato.push.app.model.LoginUser;

import java.util.Date;


/**
 * jwt token 生成工具类
 * @author sgz
 */
public class JwtTokenUtil {

    private static final JWTSigner SIGNER = JWTSignerUtil.createSigner("rs256",
            // 随机生成密钥对，此处用户可自行读取`KeyPair`、公钥或私钥生成`JWTSigner`
            KeyUtil.generateKeyPair(AlgorithmUtil.getAlgorithm("rs256")));

    /**
     * 生成token
     * @param loginUser 登录对象
     * @return token
     */
    public static String createToken(LoginUser loginUser){
        return JWT.create()
                .setPayload("id", loginUser.getId())
                // 过期时间
                .setPayload("exp", DateUtil.offsetDay(new Date(), 2).getTime())
                .setSigner(SIGNER)
                .sign();
    }

    /**
     * 解析token
     * @param token token
     * @return userId
     */
    public static Integer parseToken(String token){
        JWT jwt = JWT.of(token).setSigner(SIGNER);
        return (Integer) jwt.getPayload("id");
    }

    /**
     * 校验token是否可用
     * @param token token
     */
    public static void verify(String token) throws TokenParseException{
        JWT jwt = JWT.of(token).setSigner(SIGNER);
        boolean verify = jwt.verify();
        if (!verify){
            throw new TokenParseException("Token无效");
        }

        Long expireTimestamp = jwt.getPayloads().getLong("exp");
        if (System.currentTimeMillis() > expireTimestamp){
            throw new TokenParseException("Token已过期");
        }

    }




}
