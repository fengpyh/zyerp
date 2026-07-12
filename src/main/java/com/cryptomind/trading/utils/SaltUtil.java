package com.cryptomind.trading.utils;


import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class SaltUtil {

    static char[] chars = new char[]{'1', '2', '3', '4','5', '6', '7', '8', '9', '0',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    static char[] number = new char[]{'2', '3', '4', '5', '6'};

    //去除O,o,0,1,I,i,L,l,Z,z,2
    private static final char[] BASECODE = new char[]{'N', '6','j','p','9','x', 'C', '7', 'P','q', '5', 'B',
            'G', 'K', 'V', 'c','f', 'M','t','y', '8','m','n','b',
            'U','h', 'F', 'R','X', 'H','r', '3',
            'W', 'Y','s', 'A','v','g', 'T', 'd','u','e','k', 'E',
            'w', 'S' ,'a', 'D','J','Q','4'};

    private static final int BIN_LEN = BASECODE.length;

    /**
     * 生成邀请码最小长度
     */
    private static final int INVCODE_LEN = 6;

    // 生成:32位-36位随机盐值
    public static String generateSalt32_36(){
        int count = Integer.valueOf("3" + RandomStringUtils.random(1, 0, 5, false, false, number));
        return RandomStringUtils.random(count,0, chars.length,false, false, chars);
    }

    // 生成:32位-36位随机盐值
    public static String generateSalt12_16(){
        int count = Integer.valueOf("1" + RandomStringUtils.random(1, 0, 5, false, false, number));
        return RandomStringUtils.random(count,0, chars.length,false, false, chars);
    }

    // 生成:6位随机盐值
    public static String generateSalt6(){
        return RandomStringUtils.random(6,0, chars.length,false, false, chars);
    }

    // 生成:6位随机盐值+时间戳+32位-36位随机盐值
    public static String generateTimestampWithSalt(){
        return generateSalt6() +  System.currentTimeMillis() + generateSalt32_36();
    }

    /**
     * userId转换为邀请码
     * @param userId
     * @return
     */
    public static String idToCode(String userId) {
        int id = Integer.parseInt(userId);
        char[] resultbuf = new char[BIN_LEN];
        int charPos = BIN_LEN;

        // 当id除以数组长度结果大于0，则进行取模操作，并以取模的值作为数组的坐标获得对应的字符
        while (id / BIN_LEN > 0) {
            int index = id % BIN_LEN;
            resultbuf[--charPos] = BASECODE[index];
            id /= BIN_LEN;
        }

        resultbuf[--charPos] = BASECODE[id % BIN_LEN];
        // 将字符数组转化为字符串
        String result = new String(resultbuf, charPos, BIN_LEN - charPos);

        // 长度不足指定长度则随机补全
        int len = result.length();
        if (len < INVCODE_LEN) {
            result += RandomStringUtils.random(INVCODE_LEN - len,0, BIN_LEN,false, false, BASECODE);

    }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(BASECODE.length);
        List<String> list = new ArrayList<>(100000*5);

        String strId = null;
        for (int i = 500000,k=0; i < 600000; i++,k++) {
            strId = String.valueOf(i);
            String result = idToCode(strId);
            //查看前50个输出
            if(k<50){
                System.out.println(strId + " : " + result);
            }
            //存在重复则输出
            if(list.contains(result)){
                System.out.println("repeat::"+strId + " : " + result);
            } else {
                list.add(result);
            }

        }
        System.out.println(": end" );

    }
}
