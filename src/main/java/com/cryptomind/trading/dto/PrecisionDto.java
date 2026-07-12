package com.cryptomind.trading.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PrecisionDto {

    private BigDecimal breakVal;//市价单精度
    private int amtScale;//价格精度
    private int cntScale;//数量精度
}
