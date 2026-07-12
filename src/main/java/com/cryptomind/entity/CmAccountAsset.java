package com.cryptomind.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;



/**
 * cryptomind_ai;
 
use cm_trade_db;
drop table if exists account_asset;
CREATE TABLE account_asset (
    id bigint(20) NOT NULL auto_increment PRIMARY KEY,
    user_account_id bigint(20) not null,
    account_type varchar(15) not null,
    coin_id bigint(20) NOT NULL,
    coin_nm varchar(100) not null,
    avail_amount decimal(50,15) not null,
    frozen_amount decimal(50,15) not null,
    create_dt DATETIME not null,
    upd_dt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    index idx1 (user_account_id,account_type)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='account_asset';

 */

@Entity
@Table(name = "account_asset")
@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
@Builder
public class CmAccountAsset implements java.io.Serializable {


    private static final long serialVersionUID = -3832956482358288478L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

 
    @Column(name = "user_account_id")
    private Long user_account_id;

    @Column(name = "account_type")
    private String account_type;


    @Column(name = "coin_id")
    private Long coin_id;
    
    @Column(name = "coin_nm")
    private String coin_name;

    @Column(name = "avail_amount")
    private BigDecimal avail_amount;

    @Column(name = "frozen_amount")
    private BigDecimal frozen_amount;

    @Column(name = "create_dt")
    private Timestamp create_dt;

    @Column(name = "upd_dt")
    private Timestamp upd_dt;
}

