package com.cryptomind.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeDetail implements java.io.Serializable {
    private static final long serialVersionUID = 1L;


    private BigDecimal price;
    private BigDecimal count;
    private BigDecimal amount;
    private Long timestamp; //unit=mills
    private String cd;
    private Integer sequence;
    private Long taker_order_id;
    private Long maker_order_id;
}
