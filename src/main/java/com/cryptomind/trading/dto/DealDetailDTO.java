package com.cryptomind.trading.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DealDetailDTO {

    private BigDecimal dealPrice;// （成交价格）
    private BigDecimal dealCount;//（成交数量）
    private BigDecimal dealAmount;//（成交金额）
    private Long       dealTime;//（成交时间   毫秒）
    private Long       sequence;// 成交entrust detail id
    private String     side;//交易方向 买或者卖
}
