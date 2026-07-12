package com.cryptomind.trading.api_response;


import lombok.*;

import java.io.Serializable;

/**
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TradeMappingDataResult implements Serializable {

    //交易对名字 如 BMX/BTC
    private String name;
    //基础币种全称
    private String baseCoinFullName;
    //币种图标url
    private String iconUrl;
    //交易对ID
    private String symbol;
    //最小购买金额
    private String minBuyAmount;
    //最小购买单价
    private String minBuyPrice;
    //最小购买数量
    private String minBuyCount;
    //最小售卖金额
    private String minSellAmount;
    //最小售卖价格
    private String minSellPrice;
    //最小售卖数量
    private String minSellCount;
    //最大购买数量
    private String maxBuyCount;
    //最大售卖数量
    private String maxSellCount;
    //价格进度
    private Integer pricePrecision;
    //数量进度
    private Integer countPrecision;
    //币种信息中文链接
    private String officialznurl;
    //币种信息英文链接
    private String officialenurl;

    //深度开始精度
    private Integer depthStartPrecision;
    //深度结束进度
    private Integer depthEndPrecision;

    private String webPriceScale;

    private Integer priceScale;

    private String o;
    private String h;
    private String l;
    private String v;
    private String c;
    private String increase;
    private String fluctuation;
    private String sign;
    private String rate;

    //是否有交易大赛
    private Boolean hasTradingCompetition;
    //是否展示
    private Boolean isShow;
}

