package com.cryptomind.trading.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketTradeDTO {

    private String amount;
    private String count;
    private String isBuy;
    private String pre_version;
    private String price;
    private Long time;
    private String version;
    private Long sequence;
}