package com.cryptomind.trading.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FeeFixedUnFrozenReq {

    private String walletId;
    private Integer uid;
    private Integer ftrademapping;
    private Integer coinType;
    private String accountType;
    private String fcount;
    private String uuid;
    private String remark;
    private String ffees;
    private Long reqTimestamp;
    private Integer reqTimeout;
    private String oriFroWalletId;


}
