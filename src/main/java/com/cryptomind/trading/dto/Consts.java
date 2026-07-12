package com.cryptomind.trading.dto;

public class Consts {
    public final static Integer LOGIN_WITHOUT_EMAIL_CODE_VERIFY = 1;
    public final static Integer LOGIN_SEND_EMAIL_CODE = 2;
    public final static Integer LOGIN_EMAIL_CODE_VERIFIED = 3;


    public static final String HEADER_USER_ID = "X-USER-ID";
    public static final String HEADER_USER_EMAIL = "X-USER-EMAIL";
    public static final String HEADER_ROLE_ID = "AUTH-ROLE-ID";
    public static final String HEADER_ACCESS_TOKEN = "X-ACCESS-TOKEN";
}
