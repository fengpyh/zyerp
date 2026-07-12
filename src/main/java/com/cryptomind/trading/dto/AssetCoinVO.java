package com.cryptomind.trading.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Builder
public class AssetCoinVO implements Serializable {
    private String coinSymbol;
    private int coinType;
    private String coinName;
    private String coinShortName;
    private String iconUrl;
    private boolean canWithdraw;
    private boolean canRecharge;
    private int needMemo;
    private List<AssetCoinPairVO> tradingPair;
    private String withdrawFee;
    private int withdrawPrecision;
    private String minWithdrawCount;

}
