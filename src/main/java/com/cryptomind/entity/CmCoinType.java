package com.cryptomind.entity;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * cryptomind_ai; DECIMAL(precision, scale) 
 
use cm_trade_db;
drop table if exists coin_types;
CREATE TABLE coin_types (
    id bigint(20) NOT NULL auto_increment PRIMARY KEY,
    mkt varchar(15) not null,
    symbol varchar(31) not null,
    price_scale int(11) not null, 
    count_scale int(11) not null,
    total_supply decimal(50,15) not null,
    market_supply decimal(50,15) not null,
    fluid_supply decimal(50,15) not null,
    price_usd decimal(50,15) not null,
    open_24h decimal(50,15) not null,
    high_24h decimal(50,15) not null,
    low_24h decimal(50,15) not null,
    close_24h decimal(50,15) not null,
    vol_24h decimal(50,15) not null,
    avg_price_24h decimal(50,15) not null,
    status varchar(31) not null comment 'pending,active,inactive,dead',
    dw_status varchar(31) not null comment 'deposit_y, deposit_n, withdraw_y, withdraw_n, trade_y, trade_n',
    create_dt DATETIME not null,
    upd_dt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='coin_types';

 */

@Entity
@Table(name = "coin_types")
@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
@Builder
public class CmCoinType implements java.io.Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

 
    @Column(name = "mkt")
    private String mkt;

    @Column(name = "symbol")
    private String symbol;


    @Column(name = "full_nm")
    private String full_name;
    
    @Column(name = "price_scale")
    private Integer price_scale;
    
    @Column(name = "count_scale")
    private Integer count_scale;

    @Column(name = "total_supply")
    private BigDecimal total_supply;

    @Column(name = "market_supply")
    private BigDecimal market_supply;


    @Column(name = "fluid_supply")
    private BigDecimal fluid_supply;


    @Column(name = "price_usd")
    private BigDecimal price_usd;


    @Column(name = "open_24h")
    private BigDecimal open_24h;


    @Column(name = "high_24h")
    private BigDecimal order_type;

    @Column(name = "low_24h")
    private BigDecimal quote_ccy;


    @Column(name = "close_24h")
    private BigDecimal close_24h; 

    @Column(name = "vol_24h")
    private BigDecimal vol_24h;

    @Column(name = "avg_price_24h")
    private BigDecimal avg_price_24h;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "dw_status")
    private String dw_status;

    @Column(name = "create_dt")
    private Timestamp create_dt;

    @Column(name = "upd_dt")
    private Timestamp upd_dt;
}
