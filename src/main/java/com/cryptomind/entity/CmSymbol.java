package com.cryptomind.entity;

/**
 * cryptomind_ai; DECIMAL(precision, scale) 
 
use cm_trade_db;
CREATE TABLE symbols (
    id bigint(20) NOT NULL auto_increment PRIMARY KEY,
    mkt varchar(15) not null,
    base_ccy varchar(31) not null,
    quote_ccy varchar(31) not null,
    symbol varchar(31) not null,
    cd varchar(31) not null,
    price_scale int(11) not null,
    count_scale int(11) not null,
    price_usd decimal(50,15) not null,
    open_24h decimal(50,15) not null,
    high_24h decimal(50,15) not null,
    low_24h decimal(50,15) not null,
    close_24h decimal(50,15) not null,
    vol_24h decimal(50,15) not null,
    avg_price_24h decimal(50,15) not null,
    status varchar(31) not null comment 'pending,active,inactive,dead',
    create_dt DATETIME not null,
    upd_dt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='symbols';

 */

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "symbols")
@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
@Builder
public class CmSymbol implements java.io.Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

 
    @Column(name = "mkt")
    private String mkt;

    @Column(name = "base_ccy")
    private String base_ccy;

    @Column(name = "quote_ccy")
    private String quote_ccy;
    
    @Column(name = "symbol")
    private String symbol;

    @Column(name = "cd")
    private String cd;
    
    @Column(name = "price_scale")
    private Integer price_scale;
    
    @Column(name = "count_scale")
    private Integer count_scale;


    @Column(name = "price_usd")
    private BigDecimal price_usd;


    @Column(name = "open_24h")
    private BigDecimal open_24h;


    @Column(name = "high_24h")
    private BigDecimal high_24h;

    @Column(name = "low_24h")
    private BigDecimal low_24h;


    @Column(name = "close_24h")
    private BigDecimal close_24h; 

    @Column(name = "vol_24h")
    private BigDecimal vol_24h;

    @Column(name = "avg_price_24h")
    private BigDecimal avg_price_24h;
    
    @Column(name = "status")
    private String status;

    @Column(name = "create_dt")
    private Timestamp create_dt;

    @Column(name = "upd_dt")
    private Timestamp upd_dt;
}
