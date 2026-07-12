package com.cryptomind.entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
drop table stg_live_order_trades;
CREATE TABLE `stg_live_order_trades` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'Internal auto-increment ID',
    `stg_live_id` bigint(20) not null,
    `stg_run_cd` VARCHAR(50) NOT NULL COMMENT '自定义运行代码',
    `trade_id` VARCHAR(64) NOT NULL UNIQUE COMMENT 'Unique trade ID from the exchange',
    `order_id` VARCHAR(64) NOT NULL COMMENT 'Associated order ID',
    `client_order_id` VARCHAR(64) DEFAULT NULL COMMENT 'User-defined order ID',
    `symbol` VARCHAR(30) NOT NULL COMMENT 'Trading pair (e.g., BMX_USDT)',
    `side` VARCHAR(10) NOT NULL COMMENT 'buy or sell',
    `trade_role` VARCHAR(10) NOT NULL COMMENT 'maker or taker',
    `order_mode` VARCHAR(20) NOT NULL COMMENT 'spot, margin, or swap',
    `type` VARCHAR(20) NOT NULL COMMENT 'limit, market, etc.',

    `price` DECIMAL(18, 8) NOT NULL COMMENT 'Execution price',
	`cost_price` DECIMAL(18, 8) NOT NULL COMMENT 'Execution price',
    `size` DECIMAL(18, 8) NOT NULL COMMENT 'Executed volume (Base asset)',
    `notional` DECIMAL(18, 4) NOT NULL COMMENT 'Total transaction value (Quote asset)',
    `fee` DECIMAL(18, 8) NOT NULL DEFAULT 0.00000000 COMMENT 'Transaction fee amount',
    `fee_coin_name` VARCHAR(20) NOT NULL COMMENT 'Currency used for fees (e.g., USDT, BNB)',
    
    -- Timestamps
    `exchange_create_time` DATETIME(3) NOT NULL COMMENT 'Order creation time on exchange',
    `exchange_update_time` DATETIME(3) NOT NULL COMMENT 'Trade execution time on exchange',
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'System insertion time',
    
    INDEX `idx_symbol_time` (`symbol`, `exchange_update_time`),
    INDEX `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Live Trade Executions Log';

ALTER TABLE `stg_live_order_trades` ADD UNIQUE KEY `uk_trade_id` (`trade_id`);

select * from stg_live_order_trades;
 */

@Entity
@Table(name = "stg_live_order_trades")
@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StrategyLiveOrderTrade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "stg_live_id", nullable = false)
    private Long stgLiveId;

    @Column(name = "stg_run_cd", nullable = false, length = 50)
    private String stgRunCd;

    @Column(name = "trade_id", nullable = false, unique = true, length = 64)
    private String tradeId;

    @Column(name = "order_id", nullable = false, length = 64)
    private String orderId;

    @Column(name = "client_order_id", length = 64)
    private String clientOrderId;

    @Column(name = "symbol", nullable = false, length = 30)
    private String symbol;

    @Column(name = "side", nullable = false, length = 10)
    private String side;

    @Column(name = "trade_role", nullable = false, length = 10)
    private String tradeRole;

    @Column(name = "order_mode", nullable = false, length = 20)
    private String orderMode;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @Column(name = "price", nullable = false, precision = 18, scale = 8)
    private BigDecimal price;

    @Column(name = "cost_price", nullable = false, precision = 18, scale = 8)
    private BigDecimal costPrice;

    @Column(name = "size", nullable = false, precision = 18, scale = 8)
    private BigDecimal size;

    @Column(name = "notional", nullable = false, precision = 18, scale = 4)
    private BigDecimal notional;

    @Column(name = "fee", nullable = false, precision = 18, scale = 8)
    private BigDecimal fee = BigDecimal.ZERO;

    @Column(name = "fee_coin_name", nullable = false, length = 20)
    private String feeCoinName;

    @Column(name = "exchange_create_time", nullable = false)
    private LocalDateTime exchangeCreateTime;

    @Column(name = "exchange_update_time", nullable = false)
    private LocalDateTime exchangeUpdateTime;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
