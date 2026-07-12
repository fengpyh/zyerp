package com.cryptomind.trading.api_request;

import lombok.Data;

/**
 * Created by zhousl on 2019/12/12.
 */
@Data
public class EntrustQueryRequest {
    //用户id
    private Integer fusFid;

    private String symbol;//例如："55"，若查所有交易对传"0" 多个交易对以逗号隔开，目前只支持一个

    private String startTime;//例如：格式 yyyy-MM-dd 例如 : "2019-12-10"

    private String endTime;//例如：格式 yyyy-MM-dd 例如 : "2019-12-10"

    private String status;//1-未成交，2-部分成交，3-完全成交，4-撤销，5-订单撤销中，6-失败 。多个以逗号隔开，例如：当前委托传"1,2",历史委托传"3,4"

    private Integer entrustType;//0-买，1-卖。不传默认查所有订单。 例如：买单传0

    private Integer currentPage;

    private Integer pageSize;

    private Integer orderType;//0-限价单，1-市价单。不传默认查所有订单。 例如：当前委托传0，历史委托不传

    private String sourceChnl;//忽略大小写 例如：web端请求为"WEB",移动端分别为"iOS" "Android" openApi为 "openapi"


}
