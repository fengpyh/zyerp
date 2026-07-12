package com.cryptomind.entity;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.cryptomind.trading.utils.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
drop table pnl_exchanges;
CREATE TABLE pnl_exchanges (
  id              int(11) PRIMARY KEY auto_increment,
  protocol        VARCHAR(100)  NOT NULL,                     -- e.g., "REST", "WS"
  exchange_type   VARCHAR(64)  NOT NULL,                     -- e.g., "BINANCE","BITMART"
  access_key      varchar(255)         NOT NULL,
  secret_key     varchar(255)        NOT NULL,                     -- encrypted secretKey (AES-GCM, etc.)
  memo            varchar(255)         NULL,
  create_dt      DATETIME not null,
  upd_dt      TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3;

 */

@Entity
@Table(name = "pnl_exchanges")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class PnlExchange implements java.io.Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;


    @Column(nullable = false, length = 100)
    private String protocol;         // consider an enum

    @Column(name = "exchange_type", nullable = false, length = 64)
    private String exchangeType;     // consider an enum

    @Column(name = "access_key", nullable = false, length = 255)
    private String accessKey;


    @Column(name = "secret_key", nullable = false)
    private String secretKey; 

    @Column(name = "memo")
    private String memo;

    @Column(name = "create_dt")
    private Timestamp create_dt;

    @Column(name = "upd_dt")
    private Timestamp upd_dt;

    @PrePersist
    protected void onCreate() {
    	create_dt = upd_dt = Utils.getTimestamp();
    }

    @PreUpdate
    protected void onUpdate() {
    	upd_dt = Utils.getTimestamp();
    }



}
