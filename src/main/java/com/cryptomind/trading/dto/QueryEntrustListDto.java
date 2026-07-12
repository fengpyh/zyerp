package com.cryptomind.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryEntrustListDto {

    private Integer userId;

    private Integer status;

    private String message;

    private List<BigInteger> entrustIdList;
}
