package com.cryptomind.wquote;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class H24PriceDTO {

    private String symbol;
    private BigDecimal h;//K线-最高价
    private BigDecimal o;//K线-开盘价
    private BigDecimal l;//K线-最低价
    private BigDecimal c;//K线-收盘价
    private BigDecimal va;//成交额(V-Amount 按计价货币统计)
    private BigDecimal vc;//成交数量（V-Count 按交易货币统计）
    private BigDecimal vu;//USD成交额(V-USD 按美元计价统计)
    private BigDecimal cp;//24Hour涨跌幅(Change Percent) 计算公式 :涨跌幅 = （收盘价-开盘价）/收盘价
    private BigDecimal ca;//24Hour涨跌额(Change Amount)  计算公式 :涨跌幅 = 收盘价-开盘价
    private BigDecimal price;//法币价格
    private String sign;//币种编码 例：USD
    private Long time;//价格产生时间戳 单位：毫秒
    private Long tradeSeq;//撮合成交序列号
    private Long daySeqFrom;//最近24小时起始日分钟序号(含)
    private Long dayseqTo;//最近24小时截止日分钟序号(含)
}
