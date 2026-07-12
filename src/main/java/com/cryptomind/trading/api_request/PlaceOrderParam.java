package com.cryptomind.trading.api_request;

import lombok.Data;

/**
{"symbol":"BTC-USDT","entrustType":0,"price":"112950.8","count":"0.00412481","freqNo":"api-test-1755857075468","chnlSrc":1,"orderId":"webId","clientOrderId":"api","merchantId":1,"forceClose":0,"tradeMode":0,"orderMode":1,"subOrderMode":1,"fisLimit":"LIMIT"}

 */
@Data
public class PlaceOrderParam {
    private String symbol;
    private String side;
    private String price;
    private String count;
    private String client_order_id;

    private String order_type;

}
