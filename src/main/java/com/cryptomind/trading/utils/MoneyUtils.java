package com.cryptomind.trading.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.StringJoiner;

public class MoneyUtils {

    public static final int AMOUNT_PRECISION = 8;
    public static final int ENTRUST_AMOUNT_PRECISION = 10;
    public static final int FIAT_AMOUNT_PRECISION = 2;

    public static final String DEFAULT_8_AMOUNT_STRING = "0.00000000";
    public static final String DEFAULT_2_AMOUNT_STRING = "0.00";

    // 直接舍弃多余的位数
    public static BigDecimal discardByPrecision(BigDecimal value, int precision){
        if(value == null) {
            return BigDecimal.ZERO.setScale(precision, BigDecimal.ROUND_DOWN);
        }
        return value.setScale(precision, BigDecimal.ROUND_DOWN);
    }

    public static BigDecimal discardByPrecision(String value, int precision){
        if(value == null) {
            return BigDecimal.ZERO.setScale(precision, BigDecimal.ROUND_DOWN);
        }
        return new BigDecimal(value).setScale(precision, BigDecimal.ROUND_DOWN);
    }

    // 四舍五入
    public static BigDecimal scaleByPrecision(BigDecimal value, int precision){
        if (value == null) {
            return BigDecimal.ZERO.setScale(precision, BigDecimal.ROUND_HALF_UP);
        }
        return value.setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal scaleByPrecision(String value, int precision){
        if (org.apache.commons.lang3.StringUtils.isBlank(value)) {
            return BigDecimal.ZERO.setScale(precision, BigDecimal.ROUND_HALF_UP);
        }
        return scaleByPrecision(new BigDecimal(value), precision);
    }

    public static BigDecimal scaleByPrecision(double value, int precision){
        return BigDecimal.valueOf(value).setScale(precision, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal checkBalance(BigDecimal balance){
        return balance.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : balance;
    }

    public static double checkBalance(double balance){
        return balance < 0 ? 0 : balance;
    }


    public static BigDecimal divide(BigDecimal v1, BigDecimal v2, int count){
        if (v2.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return v1.divide(v2, count, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal divide(BigDecimal v1, Double v2, int count){
        if (v1 == null || v2 == null) {
            return BigDecimal.ZERO;
        }
        return divide(v1, BigDecimal.valueOf(v2), count);
    }


    public static String amountFormatString(String amount, int count) {

        if (StringUtils.isBlank(amount)) {
            return scaleByPrecision(BigDecimal.ZERO, count).toPlainString();
        }

        if (amount.contains("E")) {
            return scaleByPrecision(new BigDecimal(amount), count).toPlainString();
        }

        return amount;
    }

    public static int getNumberDecimalDigits(BigDecimal balance) {
        int decimalDigits = 0;
        String balanceStr = balance.toPlainString();
        int indexOf = balanceStr.indexOf(".");
        if(indexOf > 0){
            decimalDigits = balanceStr.length() - 1 - indexOf;
        }
        return decimalDigits;
    }

    public static String halfUpWithZeroFill(BigDecimal v, int scale) {
        if (scale < 0) {
            return "0";
        } else if (scale == 0) {
            return (new DecimalFormat("0")).format(v);
        } else {
            StringJoiner formatStr = new StringJoiner("");

            for(int i = 0; i < scale; ++i) {
                formatStr.add("0");
            }

            return (new DecimalFormat(formatStr.toString())).format(v);
        }
    }

    public static void main(String[] args) {
        System.out.println(halfUpWithZeroFill(BigDecimal.valueOf(0.1234567), 4));
        System.out.println(halfUpWithZeroFill(BigDecimal.valueOf(0.1234567), 5));
        System.out.println(halfUpWithZeroFill(BigDecimal.valueOf(0.1234567), 6));
        System.out.println(halfUpWithZeroFill(BigDecimal.valueOf(0.1234567), 7));
        System.out.println(halfUpWithZeroFill(BigDecimal.valueOf(0.1234567), 8));


        System.out.println(new BigDecimal("0.00000001").toPlainString());
        System.out.println(new BigDecimal("0.000000012").toPlainString());
        System.out.println(new BigDecimal("0.0000000123").toPlainString());
        System.out.println(new BigDecimal("0.00000001234").toPlainString());
        System.out.println(new BigDecimal("0.000000012345").toPlainString());
        System.out.println(new BigDecimal("0.00000001234567").toPlainString());
        System.out.println(new BigDecimal("0.000000012345678").toPlainString());


        System.out.println(getNumberDecimalDigits(new BigDecimal("0.000000012345678")));
        System.out.println(getNumberDecimalDigits(new BigDecimal("0.0012345678")));
        System.out.println(getNumberDecimalDigits(new BigDecimal("0.12345678")));
        System.out.println(getNumberDecimalDigits(new BigDecimal("0.678")));
    }
}
