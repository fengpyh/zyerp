package com.cryptomind.trading.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class EntrustDetailDto {
    private Integer type;
    private BigDecimal size;
     private String        feeCoinName;
     private Long       createTime;
    private BigDecimal         notional;
    private BigDecimal priceAvg;
    private String        tradeMappingName;
    private Long        detailId;
    private Integer        side;
    private BigDecimal fee;
    private Integer       tradeRole;
    private Long      entrustId;
    private Long      entrustIdCp;
    private Integer       tradeMappingId;
    private Long       userId;
}
