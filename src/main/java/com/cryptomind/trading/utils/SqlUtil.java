package com.cryptomind.trading.utils;

import java.util.Date;

public class SqlUtil {
    public static void appendCondition(Integer uid, Integer symbol, Integer[] statusList, Long startTime, Long endTime, StringBuilder sql) {
        if (uid!=null && uid>0) {
            sql.append(" and fl.userId=").append(uid);
        }
        //当前委托查询不要市价单
        String stat = org.apache.commons.lang.StringUtils.join(statusList, ',');
        if(EntrustConstant.CURRENT_ORDER_1.equals(stat)||EntrustConstant.CURRENT_ORDER_2.equals(stat)){
            sql.append(" and fl.isLimit=").append(0);
        }
        if (symbol!=null && symbol>0) {
            sql.append(" and fl.symbol=").append(symbol);
        }
        if (statusList!=null && statusList.length==1) {
            sql.append(" and fl.status=").append(statusList[0]);
            if(EntrustConstant.CURRENT_ORDER_LIMIT_1.equals(statusList[0])||EntrustConstant.CURRENT_ORDER_LIMIT_2.equals(statusList[0])){
                sql.append(" and fl.isLimit=").append(0);
            }
        } else {
            if (Utils.isNotEmpty(statusList)) {
                sql.append(" and fl.status in (");
                for (Integer element : statusList) {
                    sql.append(element).append(",");
                }
                if (sql.toString().endsWith(",")) {
                    sql.delete(sql.length() - 1, sql.length());
                }
                sql.append(")");
            }
        }

        if (startTime!=null && endTime!=null && startTime>0 && endTime>startTime) {
            String sTime = Dates.format(new Date(startTime), Dates.Type.YYYY_MM_DD_HH_MM_SS) ;
            String eTime = Dates.format(new Date(endTime), Dates.Type.YYYY_MM_DD_HH_MM_SS);

            sql.append(String.format(" and fl.createTime between '%s' and '%s' ", sTime, eTime));
        }
    }

    public static void appendCondition(Integer uid, Integer symbol, Long startTime, Long endTime, StringBuilder dataSql) {
        if(Utils.isNotBlank(uid)) {
            dataSql.append(" and fl.uid=").append(uid);
        }
        if(Utils.isNotBlank(symbol) && symbol>0) {
            dataSql.append(" and fl.ftrademapping=").append(symbol);
        }
        if (startTime!=null && endTime!=null && startTime>0 && endTime>startTime) {
            String sTime = Dates.format(new Date(startTime), Dates.Type.YYYY_MM_DD_HH_MM_SS) ;
            String eTime = Dates.format(new Date(endTime), Dates.Type.YYYY_MM_DD_HH_MM_SS);
            dataSql.append(String.format(" and fl.fcreatetime between '%s' and '%s' ",sTime,eTime));
        }
    }
}
