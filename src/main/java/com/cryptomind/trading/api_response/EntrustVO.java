package com.cryptomind.trading.api_response;


import lombok.*;

import java.util.List;

/**
 * <p></p>
 *
 * @author ryu
 * @version 2018/1/18
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EntrustVO implements java.io.Serializable {

    private Long entrustId;//订单id
    private int type; // 订单类型 0-市价单 1-限价
    private String tradeMappingName;//交易对名称
    private Integer tradeMappingId;//交易对id
    private long createTime;//创建时间
    private int side;//买卖方向 0-买 1-卖
    private String price;//委托价格
    private String priceAvg;//成交均价
    private String size;//委托数量（交易货币）
    private String notional;//	买入金额，单位计价币种（特例：市价单卖的时候为交易币种）
    private String filledNotional;//已成交金额
    private String filledSize;	//已成交数量
    private int state;//订单状态
    private Integer userId;//用户ID

    private List<EntrustDetailsVO> details;//如果移动端，则返回对应的成交记录


}