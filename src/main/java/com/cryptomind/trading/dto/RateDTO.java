package com.cryptomind.trading.dto;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * {"success":true,"code":0,"msg":null,"subMsg":null,
 * "data":{"rateList":[{"coinName":"BTC","marketCoinName":"USD","rate":"9290.114259710000"},
 * {"coinName":"ETH","marketCoinName":"USD","rate":"175.888471885000"},
 * {"coinName":"USDT","marketCoinName":"USD","rate":"1.001803173690"}]}}
 *
 */
@Data
@ToString
public class RateDTO {
    private boolean success;
    private int code;
    private DataObj data;


    @Data
    @ToString
    public static class DataObj implements Serializable {
        private List<Rate> rateList;
    }


    @Data
    @ToString
    public static class Rate implements Serializable {
        private String coinName;
        private String marketCoinName;
        private String rate;
    }
}
