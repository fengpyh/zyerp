package com.cryptomind.trading.dto;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
public class AssetCoinPairVO {
    private Integer symbol;
    private String tag;

}

