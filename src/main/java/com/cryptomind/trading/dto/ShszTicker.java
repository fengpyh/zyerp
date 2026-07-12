package com.cryptomind.trading.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShszTicker {

    private String          _id;
    private String  market;
    private String symbol;
    private String name;
    private String cd;
    private String         prev_close;
    private String          open;
    private String       close;
    private String      high;
    private String      low;
    private String      bid_1;
    private String      ask_1;
    private String      vol;
    private String      amount;
    private String            bid_price_1;
    private String            bid_vol_1;
    private String            bid_price_2;
    private String            bid_vol_2;
    private String            bid_price_3;
    private String            bid_vol_3;
    private String            bid_price_4;
    private String            bid_vol_4;
    private String            bid_price_5;
    private String            bid_vol_5;
    private String            ask_price_1;
    private String            ask_vol_1;
    private String            ask_price_2;
    private String            ask_vol_2;
    private String            ask_price_3;
    private String            ask_vol_3;
    private String            ask_price_4;
    private String            ask_vol_4;
    private String            ask_price_5;
    private String            ask_vol_5;
    private String dt;
    private String            upd_dt;//最近成交时间


}
