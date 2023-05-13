package com.dato.push.app.utils;

import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.asymmetric.SignAlgorithm;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyPair;

/**
 * 生成密钥工具类
 */
public class KeyPairWriter {

    public static void main(String[] args) {
        // 生成公私钥对
        KeyPair keyPair = generateKeyPair();
        // 将公私钥对存储到文件中
        saveKeyPairToFile( "push-app/src/main/resources/key_pair.dat", keyPair);
    }

    /**
     * 生成RSA公私钥对
     *
     * @return 生成的RSA公私钥对
     */
    public static KeyPair generateKeyPair() {
        return KeyUtil.generateKeyPair(SignAlgorithm.SHA256withRSA.getValue());
    }

    /**
     * 将RSA公私钥对保存到文件中
     *
     * @param fileName 保存RSA公私钥对的文件名
     * @param keyPair 要保存的RSA公私钥对
     */
    public static void saveKeyPairToFile(String fileName, KeyPair keyPair) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(keyPair);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}