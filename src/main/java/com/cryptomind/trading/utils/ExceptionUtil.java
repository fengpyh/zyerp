package com.cryptomind.trading.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
    public static String toString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter w = new PrintWriter(sw);
        e.printStackTrace(w);
        return sw.toString();
    }
}
