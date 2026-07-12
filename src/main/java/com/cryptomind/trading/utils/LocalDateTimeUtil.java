package com.cryptomind.trading.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public final class LocalDateTimeUtil {

    public static long toLongTimestamp(LocalDateTime localDateTime){
        if(localDateTime == null) return 0l;
        else return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
    }

    public static LocalDateTime toLocalDateTime(String dateTime){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, df);
    }


    /**
     * 获取时间间隔（毫秒）
     *
     * @return
     */
    public static long millisDiff(LocalDateTime lt, LocalDateTime gt) {
        Duration d = Duration.between(lt, gt);
        return d.toMillis();
    }

    /**
     * 获取时间间隔（秒）
     *
     * @return
     */
    public static long secondDiff(LocalDateTime lt, LocalDateTime gt) {
        Duration d = Duration.between(lt, gt);
        return d.getSeconds();
    }

    public static String toDateString(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }


    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTimeUtil.toLocalDateTime("2019-10-09 23:00:00");
        LocalDateTime localDateTime2 = LocalDateTimeUtil.toLocalDateTime("2019-10-10 00:00:00");
        System.out.println(localDateTime.compareTo(localDateTime2));
    }


}