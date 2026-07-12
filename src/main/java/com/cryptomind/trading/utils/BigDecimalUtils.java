package com.cryptomind.trading.utils;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@Slf4j
public class BigDecimalUtils {

    public static BigDecimal scale(BigDecimal value , int scale) {
        return value.setScale(scale, RoundingMode.HALF_UP);
    }
    /**
     * 提供精确的加法运算。
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static Double add(Double value1, Double value2, Integer scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.add(b2).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param value1 被减数
     * @param value2 减数
     * @return 两个参数的差
     */
    public static double sub(Double value1, Double value2, Integer scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.subtract(b2).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static Double mul(Double value1, Double value2, Integer scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(value1));
        BigDecimal b2 = new BigDecimal(Double.toString(value2));
        return b1.multiply(b2).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }
    
    public static BigDecimal mul(String value1, String value2, Integer scale) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.multiply(b2).setScale(scale, RoundingMode.DOWN);
    }

    /**
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @param scale    表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static Double divide(Double dividend, Double divisor, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(dividend));
        BigDecimal b2 = new BigDecimal(Double.toString(divisor));
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供指定数值的（精确）小数位四舍五入处理。
     *
     * @param value 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double value, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(value));
        BigDecimal one = BigDecimal.ONE;
        return b.divide(one, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 提供精确的加法运算。
     *
     * @param b1 被加数
     * @param b2 加数
     * @return 两个参数的和
     */
    public static BigDecimal add(BigDecimal b1, BigDecimal b2, Integer scale) {
        return b1.add(b2).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 提供精确的减法运算。
     *
     * @param b1 被减数
     * @param b2 减数
     * @return 两个参数的差
     */
    public static BigDecimal sub(BigDecimal b1, BigDecimal b2, Integer scale) {
        return b1.subtract(b2).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param b1 被乘数
     * @param b2 乘数
     * @return 两个参数的积
     */
    public static BigDecimal mul(BigDecimal b1, BigDecimal b2, Integer scale) {
        return b1.multiply(b2).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 提供（相对）精确的除法运算。 当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     *
     * @param b1    被除数
     * @param b2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static BigDecimal divide(BigDecimal b1, BigDecimal b2, Integer scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return b1.divide(b2, scale, RoundingMode.HALF_UP);
    }

    /**
     * 提供指定数值的（精确）小数位四舍五入处理。
     *
     * @param b     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static BigDecimal round(BigDecimal b, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return b.setScale(scale, RoundingMode.DOWN);
    }

    /**
     * 提供指定两个数值进行比较
     * b1>b2 return 1
     * b1<b2 return -1
     * b1=b2 return 0
     *
     * @param b1
     * @param b2
     * @return
     */
    public static int compareTo(BigDecimal b1, BigDecimal b2) {
        return b1.compareTo(b2);
    }


    public static String formatBigDecimal(BigDecimal val) {
        if (val == null) {
            return "null";
        }
        if (BigDecimal.ZERO.compareTo(val) == 0) {
            return "0";
        }
        return val.toString();
    }

    public static String formatBigDecimal(BigDecimal val, Integer precision) {
        if (val == null) {
            return "null";
        }
        if (BigDecimal.ZERO.compareTo(val) == 0) {
            return "0";
        }

        StringBuilder format = new StringBuilder("0.0");
        if(precision>1) {
            for(int i=0;i<precision-1;i++) {
                format.append('0');
            }
        }
        DecimalFormat fmt = new DecimalFormat(format.toString());
        String str=  fmt.format(val);
        //log.info("{}, {}= {}", val, precision, str);
        //return val.toString();
        return str;
    }

}
