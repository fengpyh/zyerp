package com.cryptomind.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;



/**
 * cryptomind_ai;
 
use cm_trade_db;
drop table if exists account_asset_arch;
CREATE TABLE account_asset_arch (
    id bigint(20) NOT NULL auto_increment PRIMARY KEY,
    aa_id bigint(20) NOT NULL,
    user_account_id bigint(20) not null,
    account_type varchar(15) not null,
    coin_id bigint(20) NOT NULL,
    coin_nm varchar(100) not null,
    avail_amount_after decimal(50,15) not null,
    frozen_amount_after decimal(50,15) not null,
    avail_change_val decimal(50,15) not null,
    avail_change_ops varchar(15) not null,
    frozen_change_val decimal(50,15) not null,
    frozen_change_ops varchar(15) not null,
    create_dt DATETIME not null,
    upd_dt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    index idx1 (user_account_id),
    index idx2 (aa_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='account_asset_arch';

 */

@Entity
@Table(name = "account_asset_arch")
@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
@Builder
public class CmAccountAssetArch implements java.io.Serializable {


    private static final long serialVersionUID = -3832956482358288478L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "aa_id")
    private Long aa_id;
    
 
    @Column(name = "user_account_id")
    private Long user_account_id;

    @Column(name = "account_type")
    private String account_type;


    @Column(name = "coin_id")
    private Long coin_id;
    
    @Column(name = "coin_nm")
    private String coin_name;

    @Column(name = "avail_amount_after")
    private BigDecimal avail_amount_after;

    @Column(name = "frozen_amount_after")
    private BigDecimal frozen_amount_after;

    @Column(name = "avail_change_val")
    private BigDecimal avail_change_val;
    
    @Column(name = "avail_change_ops")
    private String avail_change_ops;

    @Column(name = "frozen_change_val")
    private BigDecimal frozen_change_val;

    @Column(name = "frozen_change_ops")
    private String frozen_change_ops;
    
    @Column(name = "create_dt")
    private Timestamp create_dt;

    @Column(name = "upd_dt")
    private Timestamp upd_dt;
}

