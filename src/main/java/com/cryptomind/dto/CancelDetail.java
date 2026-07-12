package com.cryptomind.dto;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class CancelDetail implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String side;
    //private Integer userId;
    private BigDecimal price;
    private BigDecimal count;
    private BigDecimal amount;
    private String cd;
    private long timestamp;

    private Integer sequence;
}
