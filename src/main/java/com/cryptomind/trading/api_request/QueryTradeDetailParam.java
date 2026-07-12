package com.cryptomind.trading.api_request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryTradeDetailParam implements Serializable {
    @JsonProperty("entrustId")
    private Long entrustId;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("sourceChnl")
    private String sourceChnl;
}
