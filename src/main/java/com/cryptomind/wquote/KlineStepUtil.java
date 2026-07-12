package com.cryptomind.wquote;

import org.joda.time.DateTime;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class KlineStepUtil {

    /**
     * 获取在指定时间是当年的第几月
     *
     * @return
     */
    public static int getMonthOfYear(long timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(timestamp));
        int month = cal.get(Calendar.MONTH);
        return month+1;
    }
    /**
     * 获取在指定时间是当年第几周
     *
     * @return
     */
    public static int getWeekOfYear(long timestamp) {
        DateTime time = new DateTime(timestamp);
        return time.getWeekOfWeekyear();
    }

    public static int getWeekYear(long timestamp) {
        DateTime time = new DateTime(timestamp);
        return time.getWeekyear();
    }
    /**
     * 获取在指定时间月份的第几天
     *
     * @return
     */
    public static int getDayOfMonth(long timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(timestamp));
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取在指定时间年份的第几天
     *
     * @return
     */
    public static int getDayOfYear(long timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(timestamp));
        int day = cal.get(Calendar.DAY_OF_YEAR);
        return day;
    }


    /**
     * 按小时分段查询当日第几个分段
     * @param timestamp
     * @param hourStep
     * @return
     */
    public static int getHourCntOfDay(long timestamp,long hourStep){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date(timestamp));
        cal1.set(Calendar.HOUR_OF_DAY,0);
        cal1.set(Calendar.MINUTE,0);
        cal1.set(Calendar.SECOND,0);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(new Date(timestamp));

        if (cal2.before(cal1)){
            cal1.set(Calendar.DAY_OF_YEAR,cal1.get(Calendar.DAY_OF_YEAR)-1);
        }

        long stamp = cal2.getTimeInMillis() - cal1.getTimeInMillis();

        long hourMillis = hourStep*60*60*1000;
        return (int)(stamp/hourMillis) + 1;

    }

    /**
     * 按分钟分段查询当日第几个分段
     * @param timestamp
     * @param minuteStep
     * @return
     */
    public static int getMinuteCntOfDay(long timestamp,long minuteStep){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date(timestamp));
        cal1.set(Calendar.HOUR_OF_DAY,0);
        cal1.set(Calendar.MINUTE,0);
        cal1.set(Calendar.SECOND,0);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(new Date(timestamp));

        if (cal2.before(cal1)){
            cal1.set(Calendar.DAY_OF_YEAR,cal1.get(Calendar.DAY_OF_YEAR)-1);
        }

        long stamp = cal2.getTimeInMillis() - cal1.getTimeInMillis();

        long minuteMillis = minuteStep*60*1000;
        return (int)(stamp/minuteMillis) + 1;

    }

    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final int MINUTE_TYPE = 0;
    public static final int HOUR_TYPE = 1;
    public static final int DAY_TYPE = 2;
    public static final int WEEK_TYPE = 3;
    public static final int MONTH_TYPE = 4;
    public static String getDaySeq(int stepType,long timestamp,long step){
        //按日分段 分钟和小时
        DecimalFormat df=new DecimalFormat("0000");
        if (stepType == MINUTE_TYPE || stepType == HOUR_TYPE){
            long stepMillis = 60 * 1000;
            if (stepType == MINUTE_TYPE){
                stepMillis = step * stepMillis;
            }else if (stepType == HOUR_TYPE){
                stepMillis = step * 60 * stepMillis;
            }
            int stepCnt = (int)(getStamp(timestamp)/stepMillis) + 1;
            String stepStr=df.format(stepCnt);
            String ymd = getYMD(timestamp);
            return ymd + stepStr;
        }else if (stepType == DAY_TYPE){
            return new SimpleDateFormat("yyyy").format(timestamp) + df.format(getDayOfYear(timestamp));
        }else if (stepType == WEEK_TYPE){
            //周涉及到跨年的问题 不能直接取当前年份加以拼接
            return getWeekYear(timestamp) + df.format(getWeekOfYear(timestamp));
        }else if (stepType == MONTH_TYPE){
            return new SimpleDateFormat("yyyy").format(timestamp) + df.format(getMonthOfYear(timestamp));
        }

        return null;
    }

    private static String getYMD(long timestamp){

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date(timestamp));
        cal1.set(Calendar.HOUR_OF_DAY,0);
        cal1.set(Calendar.MINUTE,0);
        cal1.set(Calendar.SECOND,0);
        Date d  = cal1.getTime();

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(new Date(timestamp));

        if (cal2.before(cal1)){
            cal1.set(Calendar.DAY_OF_YEAR,cal1.get(Calendar.DAY_OF_YEAR)-1);
            return new SimpleDateFormat(DATE_FORMAT).format(cal1.getTimeInMillis());
        }
        return new SimpleDateFormat(DATE_FORMAT).format(timestamp);
    }

    public static long getOpenTimeStamp(int stepType,long timestamp,long step){
        if (stepType == MINUTE_TYPE || stepType == HOUR_TYPE){
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(new Date(timestamp));
            cal1.set(Calendar.HOUR_OF_DAY,0);
            cal1.set(Calendar.MINUTE,0);
            cal1.set(Calendar.SECOND,0);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(new Date(timestamp));

            if (cal2.before(cal1)){
                cal1.set(Calendar.DAY_OF_YEAR,cal1.get(Calendar.DAY_OF_YEAR)-1);
            }
            if (stepType == MINUTE_TYPE){
                cal1.add(Calendar.MINUTE,(int)step*(getMinuteCntOfDay(timestamp,step)-1));
            }else if (stepType == HOUR_TYPE){
                cal1.add(Calendar.HOUR,(int)step*(getHourCntOfDay(timestamp,step)-1));
            }
            return cal1.getTimeInMillis();
        }else if (stepType == DAY_TYPE){
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(timestamp));
            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(new Date(timestamp));

            if (cal2.before(cal)){
                cal.set(Calendar.DAY_OF_YEAR,cal.get(Calendar.DAY_OF_YEAR)-1);
            }

            return cal.getTimeInMillis();
        }else if (stepType == WEEK_TYPE){
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(timestamp));
            cal.set(Calendar.DAY_OF_WEEK,2);
            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(new Date(timestamp));

            if (cal2.before(cal)){
                cal.set(Calendar.DAY_OF_YEAR,cal.get(Calendar.DAY_OF_YEAR)-7);
            }
            return cal.getTimeInMillis();
        }else if (stepType == MONTH_TYPE){
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(timestamp));
            cal.set(Calendar.DAY_OF_MONTH,1);
            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(new Date(timestamp));
            if (cal2.before(cal)){
                cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)-1);
            }
            return cal.getTimeInMillis();
        }
        return 0;
    }

    public static long getCloseTimeStamp(int stepType,long timestamp,long step){
        if (stepType == MINUTE_TYPE || stepType == HOUR_TYPE){
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(new Date(timestamp));
            cal1.set(Calendar.HOUR_OF_DAY,0);
            cal1.set(Calendar.MINUTE,0);
            cal1.set(Calendar.SECOND,0);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(new Date(timestamp));

            if (cal2.before(cal1)){
                cal1.set(Calendar.DAY_OF_YEAR,cal1.get(Calendar.DAY_OF_YEAR)-1);
            }
            if (stepType == MINUTE_TYPE){
                cal1.add(Calendar.MINUTE,(int)step*getMinuteCntOfDay(timestamp,step));
            }else if (stepType == HOUR_TYPE){
                cal1.add(Calendar.HOUR,(int)step*getHourCntOfDay(timestamp,step));
            }
            return cal1.getTimeInMillis();
        }else if (stepType == DAY_TYPE){
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(timestamp));
            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(new Date(timestamp));

            if (!cal2.before(cal)){
                cal.set(Calendar.DAY_OF_YEAR,cal.get(Calendar.DAY_OF_YEAR)+1);
            }

            return cal.getTimeInMillis();
        }else if (stepType == WEEK_TYPE){
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(timestamp));
            cal.set(Calendar.DAY_OF_WEEK,2);
            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(new Date(timestamp));

            if (!cal2.before(cal)){
                cal.set(Calendar.DAY_OF_YEAR,cal.get(Calendar.DAY_OF_YEAR)+7);
            }
            return cal.getTimeInMillis();
        }else if (stepType == MONTH_TYPE){
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(timestamp));
            cal.set(Calendar.DAY_OF_MONTH,1);
            cal.set(Calendar.HOUR_OF_DAY,0);
            cal.set(Calendar.MINUTE,0);
            cal.set(Calendar.SECOND,0);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(new Date(timestamp));
            if (!cal2.before(cal)){
                cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)+1);
            }
            return cal.getTimeInMillis();
        }
        return 0;
    }

    public static String getDaySeqBeforeDay(long timestamp){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(timestamp));
        cal.set(Calendar.DAY_OF_YEAR,cal.get(Calendar.DAY_OF_YEAR)-1);
        return getDaySeq(0,cal.getTimeInMillis(),1);
    }

    public static long getStamp(long timestamp){
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(new Date(timestamp));
        cal1.set(Calendar.HOUR_OF_DAY,0);
        cal1.set(Calendar.MINUTE,0);
        cal1.set(Calendar.SECOND,0);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(new Date(timestamp));

        if (cal2.before(cal1)){
            cal1.set(Calendar.DAY_OF_YEAR,cal1.get(Calendar.DAY_OF_YEAR)-1);
        }

        long stamp = cal2.getTimeInMillis() - cal1.getTimeInMillis();

        return stamp;
    }

    public static String lowerFirst(String oldStr){

        char[]chars = oldStr.toCharArray();

        chars[0] += 32;

        return String.valueOf(chars);

    }



    private static void print(int year,int month,int date) {
        Calendar c = new GregorianCalendar();
        c.set(year, month-1, date, 23, 59, 59);
        long timestamp = c.getTimeInMillis();

        DateTime time = new DateTime(timestamp);
        DecimalFormat df=new DecimalFormat("0000");
        String weekDaySeq = new SimpleDateFormat("yyyy").format(timestamp) + df.format(getWeekOfYear(timestamp));
        String weekDaySeq2 = time.getWeekyear() + df.format(getWeekOfYear(timestamp));

        System.out.println("===========================");
        System.out.println("old:" + weekDaySeq);
        System.out.println("new:" + weekDaySeq2);
    }


    public static void main(String[] args) {

//        print(2020,12,27);
//        print(2020,12,28);
//        print(2020,12,29);
//        print(2020,12,30);
//        print(2020,12,31);
//        print(2021,1,1);
//        print(2021,1,2);
//        print(2021,1,3);
//        print(2021,1,4);
//        print(2021,1,5);

        print(2021,12,25);
        print(2021,12,26);
        print(2021,12,27);
        print(2021,12,28);
        print(2021,12,29);
        print(2021,12,30);
        print(2021,12,31);
        print(2022,1,1);
        print(2022,1,2);
        print(2022,1,3);
        print(2022,1,4);
        print(2022,1,5);
    }
}
