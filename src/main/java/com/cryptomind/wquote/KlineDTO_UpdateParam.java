package com.cryptomind.wquote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KlineDTO_UpdateParam  {

    private String       stepStr;
    private String          _id;
    private Integer         trademappingId;
    private String          trademappingName;
    private BigDecimal      h;//K线-最高价
    private BigDecimal      o;//K线-开盘价
    private BigDecimal      l;//K线-最低价
    private BigDecimal      c;//K线-收盘价
    private BigDecimal      lc;//上一根kline收盘价格
    private BigDecimal      v;//成交数量
    private Long            ot;//开盘时间戳-单位:秒
    private Long            ct;//收盘时间戳-单位:秒
    private BigDecimal      vu;//美元计价成交额(V-USD)
    private BigDecimal      va;//成交额(V-Amount)
    private BigDecimal      cp;//Kline涨跌幅(Change Percent)
    private BigDecimal      ca;//Kline涨跌额(Change Amount)
    private Long            tradeSeq;//撮合序列号
    private Long            daySeq;//当天第几根线 例：201910091440
    private Long            lt;//最近成交时间
}
