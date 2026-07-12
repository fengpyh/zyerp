package com.cryptomind.trading.dto;

import lombok.Data;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Data
@ToString
public class AuthBean {
    private Long          userId;
    private String        apiMemo;
    private String        apiSecret;
    private Long          keyExpireTimeAt;
    private String        whiteIpList;
    private boolean       enabled;
    private String permission;
}
