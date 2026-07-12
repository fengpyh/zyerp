package com.cryptomind.trading.api_response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class EntrustRes {

    private Long id;

    private String freqNo;

    //交易对id
    private Integer symbol;

    //用户id
    private Integer userId;

    private Integer entrustType;

    //订单价格
    private BigDecimal price;

    //金额
    private BigDecimal amount;

    //手续费
    private BigDecimal fees;

    private BigDecimal leftfeest = new BigDecimal(0);

    //成交金额
    private BigDecimal successAmount;

    //数量
    private BigDecimal count;

    //未成交数量
    private BigDecimal leftCount;

    //订单状态 1:未成交 2:部分成交 3:完全成交 4:成交
    private Integer status;

    //是否是限价单 0:限价单 1:市价单
    @JsonIgnore
    private Boolean isLimit;// 按照市价完全成交的订单

    //创建时间
    private Timestamp createTime; //修改表 自动创建创建时间和更新时间

    //更新时间
    private Timestamp updateTime;

    private Integer hasSubscription = 1;

    private Integer version = 0;

    @JsonProperty("isLimit")
    public Boolean getIsLimit() {
        return isLimit;
    }
    @JsonProperty("limit")
    public void setIsLimit(Boolean limit) {
        isLimit = limit;
    }
}
