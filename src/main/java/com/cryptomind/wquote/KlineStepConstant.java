package com.cryptomind.wquote;

public class KlineStepConstant {

    public static final int STEP_1_MINUTE = 1;
    public static final int STEP_5_MINUTE = 5;
    public static final int STEP_15_MINUTE = 15;
    public static final int STEP_30_MINUTE = 30;
    public static final int STEP_1_HOUR = 60;
    public static final int STEP_2_HOUR = 120;
    public static final int STEP_4_HOUR = 240;
    public static final int STEP_1_DAY = 1440;
    public static final int STEP_1_WEEK = 10080;
    public static final int STEP_1_MONTH = 43200;

    public static int[] STEPS = new int[]{
            STEP_1_MINUTE,
            STEP_5_MINUTE,
            STEP_15_MINUTE,
            STEP_30_MINUTE,
            STEP_1_HOUR,
            STEP_2_HOUR,
            STEP_4_HOUR,
            STEP_1_DAY,
            STEP_1_WEEK,
            STEP_1_MONTH,


    };
}
