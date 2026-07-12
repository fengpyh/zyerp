package com.cryptomind.trading.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FeeFixedUnFrozenReply {
    private String message;
    private String errcode;
    private Boolean flag;
    private String walletId;
    private String uuid;
}
