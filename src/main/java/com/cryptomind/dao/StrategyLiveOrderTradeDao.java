package com.cryptomind.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cryptomind.entity.StrategyLiveOrderTrade;

public interface StrategyLiveOrderTradeDao  extends JpaRepository<StrategyLiveOrderTrade, Long> {

	// 1. 根据交易所的 tradeId 查找，用于插入前的重复性校验
    Optional<StrategyLiveOrderTrade> findByTradeId(String tradeId);

    // 2. 查询某次实盘运行(stgLiveId)下的所有交易流水，按时间倒序排列
    List<StrategyLiveOrderTrade> findByStgLiveIdOrderByExchangeUpdateTimeDesc(Long stgLiveId);

    // 3. 根据系统订单ID(orderId)查询其关联的所有成交明细（部分成交时会产生多条 trade）
    List<StrategyLiveOrderTrade> findByOrderId(String orderId);
	
}
