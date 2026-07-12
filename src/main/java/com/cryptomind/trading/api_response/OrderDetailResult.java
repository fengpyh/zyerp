package com.cryptomind.trading.api_response;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResult implements Serializable {
    private Long entrustId;//订单id
    private Integer type; // 订单类型 0-市价单 1-限价
    private String tradeMappingName;//交易对名称
    private Long createTime;//创建时间
    private Integer side;//买卖方向 0-买 1-卖
    private String price;//委托价格
    private String priceAvg;//成交均价
    private String size;//委托数量（交易货币）
    private String notional;//	买入金额，单位计价币种（特例：市价单卖的时候为交易币种）
    private String filledNotional;//已成交金额
    private String filledSize;	//已成交数量
    private Integer state;//订单状态
}
