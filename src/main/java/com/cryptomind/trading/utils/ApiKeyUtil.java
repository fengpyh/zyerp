package com.cryptomind.trading.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@Slf4j
public class ApiKeyUtil {
    //生成 app_secret 密钥
    private final static String SERVER_NAME = "21mfStudio";
    private final static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * @Description: <p>
     * 短8位UUID思想其实借鉴微博短域名的生成方式，但是其重复概率过高，而且每次生成4个，需要随即选取一个。
     * 本算法利用62个可打印字符，通过随机生成32位UUID，由于UUID都为十六进制，所以将UUID分成8组，每4个为一组，然后通过模62操作，结果作为索引取出字符，
     * 这样重复率大大降低。
     * 经测试，在生成一千万个数据也没有出现重复，完全满足大部分需求。
     * </p>
     * @author mazhq
     * @date 2019/8/27 16:16
     */
    public static String getAppId() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();

    }

    /**
     * <p>
     * 通过appId和内置关键词生成APP Secret
     * </P>
     * @author mazhq
     * @date 2019/8/27 16:32
     */
    public static String getAppSecret(String appId) {
        try {
            String[] array = new String[]{appId, SERVER_NAME};
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < array.length; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            //MessageDigest md = MessageDigest.getInstance("SHA-1");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (NoSuchAlgorithmException e) {
            log.info("ERROR: {}", ExceptionUtil.toString(e));
            throw new RuntimeException();
        }
    }

    public static String sha256_mac(String message,String key){
        String outPut= null;
        try{
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(),"HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes());
            outPut = byteArrayToHexString(bytes);
        }catch (Exception e){
           log.warn("Error HmacSHA256========"+e.getMessage());
        }
        return outPut;
    }
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                sb.append('0');
            sb.append(stmp);
        }
        return sb.toString().toLowerCase();
    }

    public static void main(String[] args) {
        String appId = getAppId();
        String appSecret = getAppSecret(appId);
        System.out.println("appId: "+appId);
        System.out.println("appSecret: "+appSecret);
    }
}
