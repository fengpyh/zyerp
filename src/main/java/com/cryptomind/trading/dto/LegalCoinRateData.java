package com.cryptomind.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LegalCoinRateData implements Serializable {

    private String local;
    private Double rate;
    private String sign;

}