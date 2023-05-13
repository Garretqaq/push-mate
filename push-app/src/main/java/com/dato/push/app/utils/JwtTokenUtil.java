package com.dato.push.app.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.crypto.asymmetric.SignAlgorithm;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.dato.push.app.exception.TokenParseException;
import com.dato.push.app.model.LoginUser;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.KeyPair;
import java.util.Date;


/**
 * jwt token 生成工具类
 * @author sgz
 */
public class JwtTokenUtil {

    private static final JWTSigner SIGNER;

    private static KeyPair keyPair;

    static {
        // 初始密钥对
        initKeyPair();
        SIGNER = JWTSignerUtil.createSigner(SignAlgorithm.SHA256withRSA.getValue(), keyPair);
    }

    private static void initKeyPair() {
        try {
            InputStream stream = ResourceUtil.getStream("key_pair.dat");
            ObjectInputStream ois = new ObjectInputStream(stream);
            keyPair = (KeyPair) ois.readObject();
            ois.close();
            stream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

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
