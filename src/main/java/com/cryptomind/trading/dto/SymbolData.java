package com.cryptomind.trading.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 */
@Data
@NoArgsConstructor
@ToString
public class SymbolData implements Serializable {
    private int marketCoinId;  // 币种 id
    private String marketCoinName;  // 币种 名称 ETH
    private String marketCoinFullName;  // 币种 全称
    private long marketCoinAddTime;  // 币种 名称 ETH
    private String marketOfficialZnUrl;
    private String marketOfficialEnUrl;


    public Integer getBaseCoinId() {
        return coinId;
    }
    public Integer getQuoteCoinId() {
        return marketCoinId;
    }

    private int coinId;  // 币种 id
    private String coinName;  // 币种 名称  BMX
    private String coinFullName;  // 币种 全称
    private String coinOfficialZnUrl;
    private String coinOfficialEnUrl;
    private boolean isValuation;  // 是否使用来估值
    private boolean isAutoAsyncOuterRate;  // 是否使用来估值
    private int tradeMappingId; // 交易对id
    private int count1; // 交易对id
    private int count2; // 交易对id
    private String coinUrl;
    private String marketCoinUrl;
    private int status; // 状态
    private int scalenum; //深度当我

    private BigDecimal minBuyPrice;
    private BigDecimal minSellPrice;
    private BigDecimal minBuyAmount;
    private BigDecimal minSellAmount;
    private BigDecimal minBuyCount;
    private BigDecimal maxBuyCount;
    private BigDecimal minSellCount;
    private BigDecimal maxSellCount;

    private int state;//状态：0-未上架；1-上架；2-临时下架；3-永久下架
    private BigDecimal ftraderate;//开盘价
    private boolean isShow;// 是否展示；0-不展示；1-展示

    public SymbolData(int marketCoinId, String marketCoinName, String marketCoinFullName, String marketCoinUrl, long marketCoinAddTime,
                      int coinId, String coinName,String coinFullName, String coinUrl, int tradeMappingId, int count1, int count2, boolean isValuation, boolean isAutoAsyncOuterRate, int status, int scalenum,
                      BigDecimal minBuyPrice, BigDecimal minSellPrice, BigDecimal minBuyAmount, BigDecimal minSellAmount,
                      BigDecimal minBuyCount, BigDecimal maxBuyCount, BigDecimal minSellCount, BigDecimal maxSellCount,int state,BigDecimal ftraderate, boolean isShow) {
        this.marketCoinId = marketCoinId;
        this.marketCoinName = marketCoinName;
        this.marketCoinFullName = marketCoinFullName;
        this.marketCoinUrl = marketCoinUrl;
        this.marketCoinAddTime = marketCoinAddTime;
        this.coinId = coinId;
        this.coinName = coinName;
        this.coinFullName = coinFullName;
        this.coinUrl = coinUrl;
        this.tradeMappingId = tradeMappingId;
        this.count1 = count1;
        this.count2 = count2;
        this.isValuation = isValuation;
        this.isAutoAsyncOuterRate = isAutoAsyncOuterRate;
        this.status = status;
        this.scalenum = scalenum;
        this.minBuyPrice = minBuyPrice;
        this.minSellPrice = minSellPrice;
        this.minBuyAmount = minBuyAmount;
        this.minSellAmount = minSellAmount;
        this.minBuyCount = minBuyCount;
        this.maxBuyCount = maxBuyCount;
        this.minSellCount = minSellCount;
        this.maxSellCount = maxSellCount;
        this.state = state;
        this.ftraderate = ftraderate;
        this.isShow = isShow;
    }


    public String getSymbolName() {
        return this.coinName + "-" + this.marketCoinName;
    }


    public String getSymbol() {
        return this.coinName + "-" + this.marketCoinName;
    }
}

