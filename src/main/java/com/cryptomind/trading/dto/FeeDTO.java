package com.cryptomind.trading.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


/**
 * @Auther:superbyte
 * @Date:2020-05-12 16:21
 * @Description
 */
@Data
@Builder
public class FeeDTO {
    private BigDecimal fee;
    private Integer tradeMappingId;
    private Integer feeMode;

    public FeeDTO defaultFeeDto(Integer tradeMappingId, Integer feeMode) {
        this.fee = BigDecimal.ZERO;
        this.feeMode = feeMode;
        this.tradeMappingId = tradeMappingId;
        return this;
    }
}
