package com.cryptomind.trading.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FeeFrozenAndUnFrozenRsp {

    private String message;
    private String errcode;
    private Boolean flag;
    private String fenId;
    private Integer coinType;
    private String accountType;

    private String remark;

    private String fcount;

    private Integer uid;
}
