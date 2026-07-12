package com.cryptomind.trading.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TradeEntrustDto implements java.io.Serializable {

    private static final long serialVersionUID = 6594891806634035714L;

    private Long entrustId;
    private Integer entrustType;
    private Integer userId;
    private Boolean isLimit;
    private BigDecimal price;
    private BigDecimal count;
    private String uniqueId;

}
