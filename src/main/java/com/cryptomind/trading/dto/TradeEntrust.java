package com.cryptomind.trading.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeEntrust implements java.io.Serializable {
    private static final long serialVersionUID = 1L;




    private Long entrustId;
    private Integer entrustType;
    private Integer userId;
    private Boolean isLimit;
    private BigDecimal price;
    private BigDecimal count;
    private BigDecimal leftCount;
    private String uuid;

}
