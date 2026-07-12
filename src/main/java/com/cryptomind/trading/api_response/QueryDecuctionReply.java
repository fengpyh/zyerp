package com.cryptomind.trading.api_response;

import lombok.Data;

@Data
public class QueryDecuctionReply {
    private Long userId;
    private String deduction;
    private String deductionDiscount;//抵扣折扣
}
