package com.cryptomind.trading.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class H24PriceDTO {

    private String          symbol;
    private BigDecimal      h;//K线-最高价
    private BigDecimal      o;//K线-开盘价
    private BigDecimal      l;//K线-最低价
    private BigDecimal      c;//K线-收盘价
    private BigDecimal      va;//成交额(V-Amount 按计价货币统计)
    private BigDecimal      vc;//成交数量（V-Count 按交易货币统计）
    private BigDecimal      vu;//USD成交额(V-USD 按美元计价统计)
    private BigDecimal      cp;//24Hour涨跌幅(Change Percent) 计算公式 :涨跌幅 = （收盘价-开盘价）/收盘价
    private BigDecimal      ca;//24Hour涨跌额(Change Amount)  计算公式 :涨跌幅 = 收盘价-开盘价
    private Long            time;//价格产生时间戳 单位：毫秒

    public Timestamp getTimeObject() {
        return new Timestamp(this.time);
    }

    private String baseCoin;
    private String quoteCoin;
    private String baseCoinName;

    public String getDisplayName() {
        return this.symbol.replace('-','/');
    }

    public String getBaseCoin() {
        int i = this.symbol.indexOf('-');
        if(i>0) {
            return this.symbol.substring(0, i);
        }else {
            return this.symbol.replace('-', '/');
        }
    }
}
