package com.cryptomind.trading.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <p></p>
 *
 * @author
 * @version 2018/6/29
 */
public abstract class IncreaseUtils {

    public static String calIncrease(Double openPrice, Double closePrice){
        DecimalFormat df = new DecimalFormat("#0.00");

        String increase = "";
        if (closePrice.compareTo(openPrice) == 0) {
            increase = "1";
        } else if (closePrice.compareTo(openPrice) > 0) {
            increase = "1";
        } else {
            increase = "0";
        }
        return increase;
    }


    public static String calFluctuation(Double openPrice, Double closePrice){
        DecimalFormat df = new DecimalFormat("#0.00");

        String fluctuation = "";
        if (closePrice.compareTo(openPrice) == 0) {
            fluctuation = "0%";
        } else if (closePrice.compareTo(openPrice) > 0) {
            double num1 = openPrice;
            double num2 = closePrice;

            fluctuation = df.format(((num2 - num1) / num1) * 100.00) + "%";
        } else {
            double num1 = closePrice;
            double num2 = openPrice;

            fluctuation = "-" + df.format(((num2 - num1) / num2) * 100.00) + "%";
        }
        return fluctuation;
    }

    public static String calIncreaseV2(Double openPrice, Double closePrice){
        DecimalFormat df = new DecimalFormat("#0.0000");

        String increase = "";
        if (closePrice.compareTo(openPrice) == 0) {
            increase = "2";
        } else if (closePrice.compareTo(openPrice) > 0) {
            increase = "1";

        } else {
            increase = "0";
        }
        return increase;
    }

    public static String calFluctuationV2(Double openPrice, Double closePrice){
        DecimalFormat df = new DecimalFormat("#0.0000");

        String fluctuation = "";
        if (closePrice.compareTo(openPrice) == 0) {
            fluctuation = "0";
        } else if (closePrice.compareTo(openPrice) > 0) {
            BigDecimal num1 = BigDecimal.valueOf(openPrice);
            BigDecimal num2 = BigDecimal.valueOf(closePrice);

            fluctuation = "+" + df.format(num2.subtract(num1).divide(num1, 4, BigDecimal.ROUND_HALF_UP));
        } else {
            BigDecimal num1 = BigDecimal.valueOf(closePrice);
            BigDecimal num2 = BigDecimal.valueOf(openPrice);

            fluctuation = "-" + df.format(num2.subtract(num1).divide(num2, 4, BigDecimal.ROUND_HALF_UP));
        }
        return fluctuation;
    }
    public static String calRate(BigDecimal faitPrice) {
        Double rate = faitPrice.doubleValue();
        Double price = 1d;
        return calRate(rate, price);
    }

    public static String calRate(Double rate, double price) {
        if (rate != null) {
            if (rate * price < 1) {
                if (rate * price <= 0.00001)
                    return Utils.getDoubleZeroFill(rate * price, 8);
                if (rate * price <= 0.0001)
                    return Utils.getDoubleZeroFill(rate * price, 7);
                if (rate * price <= 0.001)
                    return Utils.getDoubleZeroFill(rate * price, 6);
                if (rate * price <= 0.01)
                    return Utils.getDoubleZeroFill(rate * price, 5);
                if (rate * price <= 0.1)
                    return Utils.getDoubleZeroFill(rate * price, 4);
                return  Utils.getDoubleZeroFill(rate * price, 3);
            } else {
                return Utils.getDoubleZeroFill(rate * price, 2);
            }
        }
        return "0.00000";
    }
}
