package com.cryptomind.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;

/*
 * 
CREATE TABLE `stg_id_names` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `name` varchar(100) NOT NULL COMMENT '策略名称',
  `code` varchar(100) NOT NULL UNIQUE COMMENT '策略唯一编码',
  `trigger_type` varchar(50) NOT NULL COMMENT '触发类型：CRON, EVENT, MANUAL, API',
  `language_tag` varchar(50) NOT NULL COMMENT '语言标签：PYTHON, JAVA, CPP, PINE, GO',
  `strategy_type_tag` varchar(50) NOT NULL COMMENT '策略类型标签：TREND(趋势), REVERSION(均值回归), ARBITRAGE(套利), GRID(网格)',
  `priority` int(11) DEFAULT '1' COMMENT '执行优先级',
  `rule_expression` text COMMENT '规则表达式',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  index(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='策略基础信息表';


ALTER TABLE `stg_id_names` 
ADD COLUMN `language_tag` varchar(50) NOT NULL COMMENT '语言标签：PYTHON, JAVA, CPP, PINE_SCRIPT, GO' AFTER `trigger_type`,
ADD COLUMN `strategy_type_tag` varchar(50) NOT NULL COMMENT '策略类型标签：TREND_FOLLOWING(趋势), MEAN_REVERSION(均值回归), ARBITRAGE(套利), GRID(网格), HIGH_FREQUENCY(高频)' AFTER `language_tag`;
 */


@Data
@Entity
@Table(name = "stg_id_names")
public class StrategyIdName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name",nullable = false, length = 100)
    private String name;

    @Column(name = "code", nullable = false, unique = true, length = 100)
    private String code;

    @Column(name = "trigger_type", nullable = false, length = 50)
    private String triggerType;
    
    @Column(name = "language_tag", nullable = false, length = 50)
    private String language_tag;
    
    @Column(name = "strategy_type_tag", nullable = false, length = 50)
    private String strategy_type_tag;

    @Column(name = "priority")
    private Integer priority;
    

    @Column(name = "rule_expression", columnDefinition = "TEXT")
    private String ruleExpression;

    @Column(name = "description")
    private String description;

    @Column(name = "status", columnDefinition = "TINYINT")
    private Integer status; // 0-禁用，1-启用

    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    
}
