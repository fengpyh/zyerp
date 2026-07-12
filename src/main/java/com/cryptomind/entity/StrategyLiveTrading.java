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

CREATE TABLE `stg_live_trading` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `stg_id` BIGINT(20) NOT NULL COMMENT 'stg_id',
    `stg_run_cd` VARCHAR(50) NOT NULL COMMENT '自定义运行代码',
    `symbol` VARCHAR(30) NOT NULL COMMENT '标的代码 (如 BTCUSDT, AAPL)',
    `direction` VARCHAR(10) NOT NULL COMMENT '多空方向: BUY(多), SELL(空)',
    `hours_run` INT NOT NULL DEFAULT 0 COMMENT '已运行小时数',
    `max_single_open` DECIMAL(18, 4) NOT NULL COMMENT '单笔最大开仓量/额',
    `leverage_risk_ratio` DECIMAL(8, 4) NOT NULL COMMENT '杠杆风控比例 (如 0.1000 表示 10%)',
    
    -- 持仓与价格数据
    `entry_price` DECIMAL(18, 8) NOT NULL DEFAULT 0.00000000 COMMENT '持仓均价',
    `current_price` DECIMAL(18, 8) NOT NULL DEFAULT 0.00000000 COMMENT '当前价格',
    `position_base` DECIMAL(18, 8) NOT NULL DEFAULT 0.00000000 COMMENT '持仓数量(Base资产,如BTC)',
    `position_quote` DECIMAL(18, 4) NOT NULL DEFAULT 0.0000 COMMENT '持仓数量(Quote资产,如USDT)',
    `used_margin` DECIMAL(18, 4) NOT NULL DEFAULT 0.0000 COMMENT '占用保证金',
    
    -- 收益与成本核心指标
    `unrealized_pnl` DECIMAL(18, 4) NOT NULL DEFAULT 0.0000 COMMENT '浮动盈亏 (未实现盈亏)',
    `realized_pnl` DECIMAL(18, 4) NOT NULL DEFAULT 0.0000 COMMENT '实际盈亏 (已实现盈亏)',
    `fee_and_slippage` DECIMAL(18, 4) NOT NULL DEFAULT 0.0000 COMMENT '手续费和滑点总计',
    `total_live_return` DECIMAL(18, 4) NOT NULL DEFAULT 0.0000 COMMENT '实盘总收益额',
    `backtest_expected_annual` DECIMAL(8, 4) NOT NULL COMMENT '回测预期年化收益率',
    `live_max_drawdown` DECIMAL(8, 4) NOT NULL DEFAULT 0.0000 COMMENT '实盘最大回撤',
    
    -- 状态与心跳
    `status` VARCHAR(20) NOT NULL DEFAULT 'RUNNING' COMMENT '运行状态: RUNNING, PAUSED, STOPPED',
    `last_heartbeat` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后心跳时间',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建/启动时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_symbol` (`symbol`),
    INDEX `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='实盘运行状态表';

ALTER TABLE `stg_live_trading` 
ADD COLUMN `initial_capital` DECIMAL(18, 4) NOT NULL DEFAULT 0.0000 COMMENT '初始市值/初始本金 (Quote资产计价)' AFTER `leverage_risk_ratio`,
ADD COLUMN `currency_type` VARCHAR(20) NOT NULL DEFAULT 'USDT' COMMENT '币种类型: USDT, CNY, USD' AFTER `initial_capital`,
ADD COLUMN `cost_price` DECIMAL(18, 8) NOT NULL DEFAULT 0.00000000 COMMENT '持仓成本价 (计入成本后的盈亏平衡价)' AFTER `entry_price`;


select * from stg_live_trading;

 */


@Entity
@Table(name = "stg_live_trading")
@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StrategyLiveTrading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "stg_id", nullable = false)
    private Long stg_id;
    
    @Column(name = "stg_run_cd", nullable = false, length = 50)
    private String stg_run_cd;
    
    @Column(name = "symbol", nullable = false, length = 30)
    private String symbol;

    @Column(name = "direction", nullable = false, length = 10)
    private String direction;

    @Column(name = "hours_run", nullable = false)
    private Integer hoursRun = 0;

    @Column(name = "max_single_open", nullable = false, precision = 18, scale = 4)
    private BigDecimal maxSingleOpen;

    @Column(name = "leverage_risk_ratio", nullable = false, precision = 8, scale = 4)
    private BigDecimal leverageRiskRatio;

    @Column(name = "initial_capital", nullable = false, precision = 8, scale = 4)
    private BigDecimal initial_capital;
    
    @Column(name = "currency_type", nullable = false, precision = 8, scale = 4)
    private String currency_type; //initial_capital 的币种单位
    
    @Column(name = "cost_price", nullable = false, precision = 8, scale = 4)
    private BigDecimal cost_price;
    // --- BASE INFO END
    
    @Column(name = "entry_price", nullable = false, precision = 18, scale = 8)
    private BigDecimal entryPrice = BigDecimal.ZERO;

    @Column(name = "current_price", nullable = false, precision = 18, scale = 8)
    private BigDecimal currentPrice = BigDecimal.ZERO;

    @Column(name = "position_base", nullable = false, precision = 18, scale = 8)
    private BigDecimal positionBase = BigDecimal.ZERO;

    @Column(name = "position_quote", nullable = false, precision = 18, scale = 4)
    private BigDecimal positionQuote = BigDecimal.ZERO;

    @Column(name = "used_margin", nullable = false, precision = 18, scale = 4)
    private BigDecimal usedMargin = BigDecimal.ZERO;

    @Column(name = "unrealized_pnl", nullable = false, precision = 18, scale = 4)
    private BigDecimal unrealizedPnl = BigDecimal.ZERO;

    @Column(name = "realized_pnl", nullable = false, precision = 18, scale = 4)
    private BigDecimal realizedPnl = BigDecimal.ZERO;

    @Column(name = "fee_and_slippage", nullable = false, precision = 18, scale = 4)
    private BigDecimal feeAndSlippage = BigDecimal.ZERO;

    @Column(name = "total_live_return", nullable = false, precision = 18, scale = 4)
    private BigDecimal totalLiveReturn = BigDecimal.ZERO;

    @Column(name = "backtest_expected_annual", nullable = false, precision = 8, scale = 4)
    private BigDecimal backtestExpectedAnnual;

    @Column(name = "live_max_drawdown", nullable = false, precision = 8, scale = 4)
    private BigDecimal liveMaxDrawdown = BigDecimal.ZERO;

    @Column(name = "status", nullable = false, length = 20)
    private String status = "RUNNING";

    @Column(name = "last_heartbeat", nullable = false)
    private LocalDateTime lastHeartbeat = LocalDateTime.now();

    @Column(name = "create_time", nullable = false, updatable = false)
    private LocalDateTime createTime = LocalDateTime.now();

    @Column(name = "update_time", nullable = false)
    private LocalDateTime updateTime = LocalDateTime.now();

    @PrePersist
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
        this.lastHeartbeat = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateTime = LocalDateTime.now();
    }
}
