package com.cryptomind.trading.api_response;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntrustDetailsVO implements java.io.Serializable {
    private Long detailId;
    private Integer type;//0-限价单 1-市价单
    private String size;
    private String feeCoinName;
    private Long createTime;
    private String notional;//成交价格
    private String priceAvg;//成交均价
    private String tradeMappingName;
    private Integer side;
    private String fee;
    private Integer tradeRole;//'成交角色 0: 无角色;1:taker;2: maker',
    private Long entrustId;//订单id
    private Long entrustIdCp;//对手订单id
    private Integer tradeMappingId;//交易对id
    private Integer userId;//交易对id

}