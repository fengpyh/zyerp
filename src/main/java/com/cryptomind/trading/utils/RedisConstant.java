package com.cryptomind.trading.utils;

import java.util.Arrays;

public class RedisConstant {
    public final static String SPLIT = "_";
    public final static String ENTRUST_HOT_PREFIX = "ENTRUST_LIST_HOT_";
    public final static String ENTRUST_COLD_PREFIX = "ENTRUST_LIST_COLD_";
    public final static String ENTRUST_LOG_HOT_PREFIX = "ENTRUST_LOG_LIST_HOT_";
    public final static String ENTRUST_LOG_COLD_PREFIX = "ENTRUST_LOG_LIST_COLD_";
    public final static Long HOT_TIME = 5L;
    public final static Long COLD_TIME = 60L;

    public static String getEntrustHotKey(Integer uid, Integer symbol, Integer[] statusList) {
        StringBuilder stringBuffer = new StringBuilder();
        Arrays.stream(statusList).forEach(stringBuffer::append);
        return ENTRUST_HOT_PREFIX + uid + RedisConstant.SPLIT + symbol + RedisConstant.SPLIT + stringBuffer.toString();
    }

    public static String getEntrustColdKey(Integer uid, Integer symbol, Integer[] statusList) {
        StringBuilder stringBuffer = new StringBuilder();
        Arrays.stream(statusList).forEach(stringBuffer::append);
        return ENTRUST_COLD_PREFIX + uid + RedisConstant.SPLIT + symbol + RedisConstant.SPLIT + stringBuffer.toString();
    }

    public static String getEntrustLogHotKey(Integer uid, Integer symbol) {
        return ENTRUST_LOG_HOT_PREFIX + uid + RedisConstant.SPLIT + symbol;
    }

    public static String getEntrustLogColdKey(Integer uid, Integer symbol) {
        return ENTRUST_LOG_COLD_PREFIX + uid + RedisConstant.SPLIT + symbol;
    }
    public static String getLatestOrderKey(int ftrademapping, int isBuy, int precision) {
        StringBuffer sb = new StringBuffer("LatestOrderKey-");
        sb
                .append(precision)
                .append("-")
                .append(ftrademapping)
                .append("-")
                .append(isBuy);
        return sb.toString();
    }
}
