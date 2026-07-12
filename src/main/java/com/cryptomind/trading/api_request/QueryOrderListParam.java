package com.cryptomind.trading.api_request;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryOrderListParam implements Serializable {
    private int current;
    private int limit;
    private int tradeMappingId;
    private String status;
    private Long userId;
}
