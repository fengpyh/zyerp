package com.cryptomind.trading.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FeeFrozenAndUnFrozenReq {
    private String fenId;
    private Integer uid;
    private Integer ftrademapping;
    private Integer coinType;
    private String accountType;
    private String fcount;
    private String uuid;
    private String ffees;
    private String remark;

}
