package com.cryptomind.trading.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtil {

    public static String base64Encode(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    public static byte[] base64Decode(String data) {
        return Base64.decodeBase64(data.getBytes());
    }


    public static String encodeBase64(String data) {
        return Base64.encodeBase64String(data.getBytes());
    }

    public static String decodeBase64(String data) {
        return new String(Base64.decodeBase64(data.getBytes()));
    }


    //MD5
    public static String md5(String data) {

        return DigestUtils.md5Hex(data);
    }

    //sha1
    public static String sha1(String data) {
        return DigestUtils.sha1Hex(data);
    }

    //sha256Hex
    public static String sha256Hex(String data) {
        return DigestUtils.sha256Hex(data);
    }

    //sha512Hex
    public static String sha512Hex(String data) {
        return DigestUtils.sha512Hex(data);
    }


    /**
     * 原始参数: 123456
     *   #	算法	结果	结果(大写)	长度	备注
     *  1	md5	e10adc3949ba59abbe56e057f20f883e	E10ADC3949BA59ABBE56E057F20F883E	32	前16位：小写：e10adc3949ba59ab 大写：E10ADC3949BA59AB
     *  2	sha1	7c4a8d09ca3762af61e59520943dc26494f8941b	7C4A8D09CA3762AF61E59520943DC26494F8941B	40
     *  3	sha256	8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92	8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92	64
     *  4	sha512	ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413	BA3253876AED6BC22D4A6FF53D8406C6AD864195ED144AB5C87621B6C233B548BAEAE6956DF346EC8C17F5EA10F35EE3CBC514797ED7DDD3145464E2A0BAB413
     * @param args
     */

}
