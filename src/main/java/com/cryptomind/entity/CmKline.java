package com.cryptomind.entity;

/**
 * cryptomind_ai; DECIMAL(precision, scale) 
 
use cm_trade_db;
drop table if exists klines;
CREATE TABLE klines (
    id varchar(100) NOT NULL PRIMARY KEY,
    symbol varchar(100) NOT NULL,
    step varchar(7) NOT NULL,
    ts_str varchar(20) NOT NULL,
    ts BIGINT NOT NULL COMMENT '开始时间，Unix时间戳(毫秒)',
    o DECIMAL(32,16) NOT NULL COMMENT '开盘价格',
    h DECIMAL(32,16) NOT NULL COMMENT '最高价格',
    l DECIMAL(32,16) NOT NULL COMMENT '最低价格',
    c DECIMAL(32,16) NOT NULL COMMENT '收盘价格',
    vol DECIMAL(32,16) NOT NULL COMMENT '交易量，以张/币为单位',
    vol_ccy DECIMAL(32,16) DEFAULT NULL COMMENT '交易量，以币为单位',
    vol_ccy_quote DECIMAL(32,16) DEFAULT NULL COMMENT '交易量，以计价货币为单位',
    confirm TINYINT(1) NOT NULL COMMENT 'K线状态：0未完结，1已完结',
    create_dt DATETIME not null,
    upd_dt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='klines';

不用自增ID, 固定1,000,000 算一下能存多少k线
1分钟线, 
1天 1,440
1年 525,600
假如 1000个标的：525,600,000(5亿)
所以这个表要提前分配好

id format: (btc_usdt_1m_yyyyMMddHHmm) (len=24)
--
还是要用mongodb
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
@Table(name = "klines")
@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
@Builder
public class CmKline implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "symbol", nullable = false)
    private String symbol;
    
    @Column(name = "step", nullable = false)
    private String step;
    
    @Column(name = "ts_str", nullable = false)
    private String ts_str;

    @Column(name = "ts", nullable = false)
    private Long ts;  // 开始时间，Unix时间戳（毫秒）

    @Column(name = "o", nullable = false)
    private BigDecimal o;  // 开盘价格

    @Column(name = "h", nullable = false)
    private BigDecimal h;  // 最高价格

    @Column(name = "l", nullable = false)
    private BigDecimal l;  // 最低价格

    @Column(name = "c", nullable = false)
    private BigDecimal c;  // 收盘价格

    @Column(name = "vol", nullable = false)
    private BigDecimal vol;  // 交易量（张/币）

    @Column(name = "vol_ccy")
    private BigDecimal volCcy;  // 交易量（币）

    @Column(name = "vol_ccy_quote")
    private BigDecimal volCcyQuote;  // 交易量（计价货币）

    @Column(name = "confirm", length = 1, columnDefinition = "BIT")
    private Boolean confirm;  // K线状态 (0未完结, 1已完结)

    @Column(name = "create_dt")
    private Timestamp create_dt;

    @Column(name = "upd_dt")
    private Timestamp upd_dt;
}
