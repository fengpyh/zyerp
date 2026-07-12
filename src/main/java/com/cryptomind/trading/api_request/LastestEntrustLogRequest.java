package com.cryptomind.trading.api_request;

import lombok.Data;


@Data
public class LastestEntrustLogRequest {
    //用户id
    private String uuid;
    private long fuid;//查询用户
    private int  size;//查询条件
}
