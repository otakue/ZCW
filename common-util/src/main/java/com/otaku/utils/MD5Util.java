package com.otaku.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Otaku
 * @date 2021/10/19-21:27
 */
public class MD5Util {
    /**
     * 为明文字符串进行md5加密
     * @param source  明文字符串
     * @return
     */
    public static String getMd5(String source) {
        if (source == null || source.length() == 0) {
            throw new NullPointerException("不是一个有效的源字符串");
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            //将明文转换为字符串
            byte[] input = source.getBytes();
            //将明文字符串转换为加密方式
            byte[] output = digest.digest(input);
            //将加密后的名为转为整数型
            BigInteger bigInteger = new BigInteger(1, output);
            //抓换位16进制
            return  bigInteger.toString(16).toUpperCase();
         } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
