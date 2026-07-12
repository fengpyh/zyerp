package com.cryptomind.trading.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

    private final static String EMPTY_STR = "";
    public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
           String str = sw.toString() ;
            sw.close();
            pw.close();
            return str;
        } catch (Exception e2) {
            return EMPTY_STR;
        }
    }
}
