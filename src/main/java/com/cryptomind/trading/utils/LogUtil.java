package com.cryptomind.trading.utils;

import org.slf4j.Logger;

/**
 * Time - LEVEL - Thread - Package - Function - EntityId –TraceId - System - Type - Message
 * Time 由文件名和前置时间共同决定，文件名中包含YY-MM-DD，前置时间包括HH-MM-SS.sss
 * LEVEL - Thread - Package 在Appender中设置
 * Function: 第1个参数
 * EntityId: 第2个参数
 * TraceId: 第3个参数
 * System: 第4个参数
 * Type: 第5个参数
 * Message: 第6个参数
 *
 * 关于Type:
 *  0: Exception
 *  1: Params
 *  2: Return – Time Used
 *  3: Info
 */
public class LogUtil {
    public static ThreadLocal<String> entityId = new ThreadLocal<>();
    public static ThreadLocal<String> traceId = new ThreadLocal<>();
    public static ThreadLocal<String> system = new ThreadLocal<>();

    public enum LogType {
        EXCEPTION,
        PARAMS,
        RETURN,
        INFO
    }

    public static void init(String trace) {
        traceId.set("trace:" + trace);
    }


    private static String genPrefix(String funcName, LogType logType) {
        String eid = entityId.get();
        String tid = traceId.get();
        String sys = system.get();
        String formatPrefix = String.format(".%s | %s | %s | %s | %s | ",
                funcName, eid, tid, sys, logType.name());
        return formatPrefix;
    }

    public static void trace(Logger logger, LogType logType, String funcName, String format, Object... arguments) {
        String formatPrefix = genPrefix(funcName, logType);
        logger.trace( formatPrefix + format, arguments);
    }

    public static void debug(Logger logger, LogType logType, String funcName, String format, Object... arguments) {
        String formatPrefix = genPrefix(funcName, logType);
        logger.debug( formatPrefix + format, arguments);
    }

    public static void info(Logger logger, LogType logType, String funcName, String format, Object... arguments) {
        String formatPrefix = genPrefix(funcName, logType);
        logger.info( formatPrefix + format, arguments);
    }

    public static void warn(Logger logger, LogType logType, String funcName, String format, Object... arguments) {
        String formatPrefix = genPrefix(funcName, logType);
        logger.warn( formatPrefix + format, arguments);
    }

    public static void error(Logger logger, LogType logType, String funcName, String format, Object... arguments) {
        String formatPrefix = genPrefix(funcName, logType);
        logger.error( formatPrefix + format, arguments);
    }

    public static void end() {

        if (traceId != null) {
            traceId.remove();
            traceId = null;
        }

    }
}
