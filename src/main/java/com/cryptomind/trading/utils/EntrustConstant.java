package com.cryptomind.trading.utils;

public class EntrustConstant {
    public final static String CURRENT_ORDER_1 = "1,2";//当前委托单查询条件为status=1，2
    public final static String CURRENT_ORDER_2 = "2,1";//当前委托单查询条件为status=1，2
    public final static String CURRENT_ORDER_STATUS_1 = "1";//当前委托单查询条件为status=1，2
    public final static String CURRENT_ORDER_STATUS_2 = "2";//当前委托单查询条件为status=1，2
    public final static Integer CURRENT_ORDER_LIMIT_1 = 1;//当前委托单查询条件为status=1
    public final static Integer CURRENT_ORDER_LIMIT_2 = 2;//当前委托单查询条件为status=2


    public static Boolean isHistoryOrder(String str) {
        if (CURRENT_ORDER_1.equals(str)) {
            return false;
        }
        if (CURRENT_ORDER_2.equals(str)) {
            return false;
        }
        if (CURRENT_ORDER_STATUS_1.equals(str)) {
            return false;
        }
        if (CURRENT_ORDER_STATUS_2.equals(str)) {
            return false;
        }
        return true;
    }
}
