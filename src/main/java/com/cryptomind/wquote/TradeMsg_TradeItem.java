package com.cryptomind.wquote;

/*
{
                "isBuy": "0",
                "sequence": 17655,
                "amount": "41.99",
                "price": "26577.70",
                "count": "0.00158",
                "time": 1695479460847
            }
 */

import lombok.Data;

@Data
public class TradeMsg_TradeItem {
    private String isBuy;
    private Long sequence;

    private String amount;

    private String price;
    private String count;
    private Long time;
}
