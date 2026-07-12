package com.cryptomind.trading.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Auther:SBT
 * @Date:2020-02-12 11:55
 * @Description
 */
public class MathUtils {
    /**
     * 计算涨跌幅
     * 保留5位小数
     *
     * @return
     */
    public static final BigDecimal computeCp(BigDecimal colsePrice, BigDecimal openPrice) {
        if (openPrice.compareTo(BigDecimal.ZERO) > 0) {
            return colsePrice.subtract(colsePrice).divide(openPrice, 5, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal max(BigDecimal a, BigDecimal b) {
        if(a==null || b==null) {
            return null;
        }
        if(a==null) {
            return b;
        }
        if(b==null) {
            return a;
        }

        if(a.compareTo(b)>=0) {
            return a;
        }else{
            return b;
        }



    }
}
