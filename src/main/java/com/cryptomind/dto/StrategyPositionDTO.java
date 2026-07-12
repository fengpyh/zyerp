package com.cryptomind.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StrategyPositionDTO {
	private String symbol;          // 标的代码 (如 BTCUSDT)
    private String direction;       // 多空方向 (BUY / SELL)
    private BigDecimal entryPrice;   // 持仓均价
    private BigDecimal currentPrice; // 当前现价
    private BigDecimal positionSize; // 持仓数量 (Base 资产数量)
    private BigDecimal usedMargin;   // 占用保证金
    
    //动态衍生字段
    private BigDecimal marketValue;  // 持仓市值
    private BigDecimal unrealizedPnl;// 浮动盈亏 (Unrealized PnL)
    
    
    /**
     * 内部构建器扩展：在调用 .build() 时自动触发金融公式计算
     * 避免在业务层随处手写计算逻辑，统一维护高精度计算规则
     */
    public static class StrategyPositionDTOBuilder {
        
        public StrategyPositionDTO build() {
            // 1. 确保基础计算字段不为 null
            BigDecimal size = this.positionSize != null ? this.positionSize : BigDecimal.ZERO;
            BigDecimal current = this.currentPrice != null ? this.currentPrice : BigDecimal.ZERO;
            BigDecimal entry = this.entryPrice != null ? this.entryPrice : BigDecimal.ZERO;
            
            // 2. 自动计算：持仓市值 = 持仓数量 * 当前现价
            this.marketValue = size.multiply(current);

            // 3. 自动计算：浮动盈亏 (根据多空方向决定公式)
            if ("BUY".equalsIgnoreCase(this.direction)) {
                // 做多盈亏 = (当前价 - 持仓均价) * 持仓数量
                this.unrealizedPnl = current.subtract(entry).multiply(size);
            } else if ("SELL".equalsIgnoreCase(this.direction)) {
                // 做空盈亏 = (持仓均价 - 当前价) * 持仓数量
                this.unrealizedPnl = entry.subtract(current).multiply(size);
            } else {
                this.unrealizedPnl = BigDecimal.ZERO;
            }

            // 调用 Lombok 自动生成的全参构造函数
            return new StrategyPositionDTO(
                    symbol, direction, entryPrice, currentPrice, positionSize, 
                    usedMargin, marketValue, unrealizedPnl
            );
        }
    }
}
