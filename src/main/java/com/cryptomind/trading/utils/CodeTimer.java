package com.cryptomind.trading.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 统计代码段执行时间。
 * 在需要进行统计的代码段调用CodeTimer.set()方法进行标记。
 * 打印时调用CodeTimer.print()方法
 */
@Slf4j
public class CodeTimer {
    private String lastMark = "start";
    private long lastTime = System.currentTimeMillis();
    private final Map<String, Long> timeMap = new LinkedHashMap<String, Long>();
    private final Map<String, Long> timeHappenCount = new LinkedHashMap<String, Long>();

    public void set(int mark) {
        set("" + mark);
    }

    public void set(String mark) {
        long thisTime = System.currentTimeMillis();
        String key = lastMark + "->" + mark + "";
        Long lastSummary = timeMap.get(key);
        if (lastSummary == null)
            lastSummary = 0L;

        timeMap.put(key, System.currentTimeMillis() - lastTime + lastSummary);
        Long lastCount = timeHappenCount.get(key);
        if (lastCount == null)
            lastCount = 0L;

        timeHappenCount.put(key, ++lastCount);
        lastTime = thisTime;
        lastMark = mark;
    }

    public void print() {
        Integer a = 0;
        if (log.isInfoEnabled()) {
            log.info(
                    String.format("%25s %18s %18s %18s",
                            "PROCESS", "TOTAL_TIME", "REPEAT_TIMES", "AVG_TIME"));
        }
//        System.out.println( String.format("%25s %18s %18s %18s",
//                "PROCESS", "TOTAL_TIME", "REPEAT_TIMES", "AVG_TIME"));
        for (Map.Entry<String, Long> entry : timeMap.entrySet()) {
            if (log.isInfoEnabled()) {
                log.info(
                        String.format("%25s %18s %18s %18s", entry.getKey(),
                                entry.getValue(), timeHappenCount.get(entry.getKey()),
                                entry.getValue() / timeHappenCount.get(entry.getKey())
                        ));
            }
//            System.out.println( String.format("%25s %18s %18s %18s", entry.getKey(),
//                    entry.getValue(), timeHappenCount.get(entry.getKey()),
//                    entry.getValue() / timeHappenCount.get(entry.getKey())
//            ));

        }
    }
}
