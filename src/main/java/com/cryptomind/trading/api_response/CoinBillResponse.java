package com.cryptomind.trading.api_response;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CoinBillResponse implements Serializable {

    //记录ID
    private Long id;

    //币种名称
    private String coinName;

    private String createTime;

    //充值时间戳
    private Long createTimestamp;

    //充值个数
    private BigDecimal amount;

    //当前确认数
    private Integer currentConfirmations;

    //成功确认数
    private Integer successConfirmations;

    //状态  0：待确认，1:确认
    private Integer status;

    //区块交易ID
    private String txid;

    private String txUrl; // 为前端提供交易超链接

    private String addressUrl; // 为前端提供地址超链接

    //提现地址
    private String fromAddress;

    //充值地址
    private String toAddress;

    //手续费
    private String fee;

    //原因说明
    private String reason;

    //错误码
    private String reasonCode;

    private String clientValue;

    private long updateTimestamp;




}
