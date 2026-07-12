package com.cryptomind.trading.api_request;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryTradeListParam implements Serializable {
    private Integer current;
    private Integer limit;
    private Long userId;
    private Integer tradeMappingId;
    //private String sourceChnl;
}
