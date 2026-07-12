package com.cryptomind.trading.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntrustQueryRequestDto {
    //用户id
    private Integer userId;

    private Integer tradeMappingId;//例如："55"，若查所有交易对传"0" 多个交易对以逗号隔开，目前只支持一个

    private Long startTime;//时间毫秒

    private Long endTime;

    private String state;//1: 下单失败 2: 下单成功 3: 冻结失败 4: 冻结成功 5: 部分成交 6: 完全成交 7: 撤销中 8: 撤销成功

    private Integer side;//0-买，1-卖。不传默认查所有订单。 例如：买单传0

    private Integer current;//当前页 这里用当前页很不好 防不了恶意传一个很大的数字

    private Integer limit;//pageSize

    private Integer type;//0-限价单，1-市价单。不传默认查所有订单。 例如：当前委托传0，历史委托不传

    private String sourceChnl;//忽略大小写 例如：web端请求为"WEB",移动端分别为"iOS" "Android" openApi为 "openapi"

    private Integer tradeRole;//成交角色 0: 无角色;1:taker;2: maker',

    private Long entrustId;//委托单id

    private String sign;
}
