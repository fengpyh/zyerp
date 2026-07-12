package com.cryptomind.entity;


import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

/*
CREATE TABLE `stg_backtest` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    `strategy_id` VARCHAR(64) NOT NULL COMMENT '策略ID/编号',
    `start_date` DATE NOT NULL COMMENT '回测开始日期',
    `end_date` DATE NOT NULL COMMENT '回测结束日期',
    `total_return` DECIMAL(18, 4) NOT NULL COMMENT '总收益率(如 0.2562 表示 25.62%)',
    `annualized_return` DECIMAL(18, 4) NOT NULL COMMENT '年化收益率',
    `sharpe_ratio` DECIMAL(8, 4) NOT NULL COMMENT '夏普比率',
    `max_drawdown` DECIMAL(18, 4) NOT NULL COMMENT '最大回撤',
    `win_rate` DECIMAL(8, 4) NOT NULL COMMENT '胜率',
    `profit_loss_ratio` DECIMAL(8, 4) NOT NULL COMMENT '盈亏比',
    `execution_status` VARCHAR(20) NOT NULL DEFAULT 'SUCCESS' COMMENT '执行状态: RUNNING, SUCCESS, FAILED',
    `error_msg` TEXT DEFAULT NULL COMMENT '异常信息',
    `detail_json` TEXT DEFAULT NULL COMMENT '存储持仓明细、收益曲线等指标扩展数据',
    `created_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_strategy_id` (`strategy_id`),
    INDEX `idx_created_time` (`created_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='策略回测结果表';


-- 样本 1：趋势跟踪策略（表现优异，高夏普比率） '双均线趋向追踪策略V1',
INSERT INTO `stg_backtest` (
    `strategy_id`,  `start_date`, `end_date`, 
    `total_return`, `annualized_return`, `sharpe_ratio`, `max_drawdown`, 
    `win_rate`, `profit_loss_ratio`, `execution_status`, `error_msg`, `detail_json`
) VALUES (
    'STRAT_MA_CROSS_001',  '2025-01-01', '2025-06-01', 
    0.2562, 0.5540, 2.1500, 0.0850, 
    0.5820, 1.8500, 'SUCCESS', NULL, 
    '{"equity_curve": [{"date": "2025-01-01", "value": 1.0}, {"date": "2025-01-31", "value": 1.0494}, {"date": "2025-03-02", "value": 1.1426}, {"date": "2025-04-01", "value": 1.1987}, {"date": "2025-05-01", "value": 1.2409}], "metrics": {"initial_capital": 1000000, "final_capital": 1256200, "total_trades": 53, "expectancy": 0.058}}'
);

-- 样本 2：均值回归策略（表现平稳，高胜率、低回撤） 'RSI均值回归反转策略', 
INSERT INTO `stg_backtest` (
    `strategy_id`,  `start_date`, `end_date`, 
    `total_return`, `annualized_return`, `sharpe_ratio`, `max_drawdown`, 
    `win_rate`, `profit_loss_ratio`, `execution_status`, `error_msg`, `detail_json`
) VALUES (
    'STRAT_RSI_REVERT_002', '2025-01-01', '2025-06-01', 
    0.1245, 0.2610, 1.4200, 0.0520, 
    0.6450, 1.3200, 'SUCCESS', NULL, 
    '{"equity_curve": [{"date": "2025-01-01", "value": 1.0}, {"date": "2025-01-31", "value": 1.0289}, {"date": "2025-03-02", "value": 1.0684}, {"date": "2025-04-01", "value": 1.1102}, {"date": "2025-05-01", "value": 1.1306}], "metrics": {"initial_capital": 1000000, "final_capital": 1124500, "total_trades": 96, "expectancy": 0.2144}}'
);

-- 样本 3：多因子Alpha策略（运行失败，触发异常/风控） '高频多因子选股Alpha策略',
INSERT INTO `stg_backtest` (
    `strategy_id`,  `start_date`, `end_date`, 
    `total_return`, `annualized_return`, `sharpe_ratio`, `max_drawdown`, 
    `win_rate`, `profit_loss_ratio`, `execution_status`, `error_msg`, `detail_json`
) VALUES (
    'STRAT_ALPHA_HIGH_003',  '2025-02-15', '2025-05-15', 
    -0.0420, -0.1610, -0.5200, 0.1240, 
    0.4210, 0.9500, 'FAILED', '触发风控主动止损，多头因子在市场风格切换中失效', 
    '{"equity_curve": [{"date": "2025-01-01", "value": 1.0}, {"date": "2025-01-31", "value": 0.9739}, {"date": "2025-03-02", "value": 0.9972}, {"date": "2025-04-01", "value": 0.9697}, {"date": "2025-05-01", "value": 0.9464}], "metrics": {"initial_capital": 1000000, "final_capital": 958000, "total_trades": 103, "expectancy": 0.2277}}'
);


 */

@Data
@Entity
@Table(name = "stg_backtest")
public class StrategyBacktestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "strategy_id", nullable = false, length = 64)
    private String strategyId;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "total_return", nullable = false, precision = 18, scale = 4)
    private BigDecimal totalReturn;

    @Column(name = "annualized_return", nullable = false, precision = 18, scale = 4)
    private BigDecimal annualizedReturn;

    @Column(name = "sharpe_ratio", nullable = false, precision = 8, scale = 4)
    private BigDecimal sharpeRatio;

    @Column(name = "max_drawdown", nullable = false, precision = 18, scale = 4)
    private BigDecimal maxDrawdown;

    @Column(name = "win_rate", nullable = false, precision = 8, scale = 4)
    private BigDecimal winRate;

    @Column(name = "profit_loss_ratio", nullable = false, precision = 8, scale = 4)
    private BigDecimal profitLossRatio;

    @Column(name = "execution_status", nullable = false, length = 20)
    private String executionStatus;

    @Column(name = "error_msg", columnDefinition = "TEXT")
    private String errorMsg;

    // 映射 MySQL 的 JSON 字段，保存复杂的曲线节点或每笔交易明细
    @Column(name = "detail_json", columnDefinition = "TEXT")
    private String detailJson;

    @Column(name = "created_time", nullable = false, updatable = false)
    private LocalDateTime createdTime;

    @Column(name = "updated_time", nullable = false)
    private LocalDateTime updatedTime;

    @PrePersist
    protected void onCreate() {
        createdTime = LocalDateTime.now();
        updatedTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTime = LocalDateTime.now();
    }
}
