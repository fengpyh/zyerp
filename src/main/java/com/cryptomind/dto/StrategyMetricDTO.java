package com.cryptomind.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyMetricDTO {
    private String name;        // 历史年化收益率
    private String value;       // +48.65%
    private String title;       // Annualized Return
    private String desc;        // 基于复利模型算出的年度期望收益
    private String borderClass; // border-profit
    private String textClass;   // text-success
}
