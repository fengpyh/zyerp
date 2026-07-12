package com.cryptomind.trading.api_request;

import lombok.Data;

/**
 * 
{"symbol":"BTC-USDT","entrustType":0,"price":"112950.8","count":"0.00412481","freqNo":"api-test-1755857075468","chnlSrc":1,"orderId":"webId","clientOrderId":"api","merchantId":1,"forceClose":0,"tradeMode":0,"orderMode":1,"subOrderMode":1,"fisLimit":"LIMIT"}

 */
@Data
public class EntrustParam {
    //主键
    private Long fid;
    //用户id
    private Integer fusFid;

    private Integer symbol;

    private Long startTime;

    private Long endTime;

    private String statuss;

    private Integer currentPage;

    private Integer pageSize;

    private String sourceChnl;

}
