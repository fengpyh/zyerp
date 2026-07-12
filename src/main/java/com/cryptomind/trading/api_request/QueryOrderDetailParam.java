package com.cryptomind.trading.api_request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryOrderDetailParam implements Serializable {

    @JsonProperty("id")
    private Long entrustId;

    @JsonProperty("userId")
    private Long userId;
}