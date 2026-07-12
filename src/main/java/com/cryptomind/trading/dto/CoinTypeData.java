package com.cryptomind.trading.dto;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 */
@Builder
@Data
@ToString
public class CoinTypeData implements Serializable {

    private int coinId;
    private String coinName;
    private String coinFullName;
    private int type;
    private boolean status;
    private String furl;
    private Integer parentCoinType;
    private BigDecimal issueUsdPrice;
    private String officialZnUrl;
    private String officialEnUrl;
    private String contractAddress;

    private String fip;
    private String fport;
    private Integer confirmations;
    private Integer withdrawConfirmations;
    private Integer gasLimit;
    private BigDecimal minWithdrawCount;
    private Integer withdrawPrecision;
    //固定手续费
    private BigDecimal withdrawFixedFee;

    //是否使用浮动手续费计算(1=是,0=否)
    private Integer withdrawUseFloatGasFee;
    //gas百分比
    private BigDecimal withdrawGasFeePercentage;

    // 提现最高手续费.本币种做单位
    private BigDecimal withdrawMaxFee;

    // 提现最低手续费.本币种做单位
    private BigDecimal withdrawMinFee;

    private String addressMatch;
    private Boolean memoEnabled;
    private String txBaseUrl;
    private String addressBaseUrl;
    private String protocol;
    private Integer chainDecimals;

    private Boolean withDrawEnabled;
    private Boolean rechargeEnabled;
    private String network;
    private Integer rechargeState;
    private Integer withdrawState;
    private Integer groupCoinType;
}

