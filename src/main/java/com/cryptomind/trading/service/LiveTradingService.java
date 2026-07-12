package com.cryptomind.trading.service;

import java.util.List;

import com.cryptomind.entity.StrategyLiveOrderTrade;
import com.cryptomind.entity.StrategyLiveTrading;

public interface LiveTradingService {

	StrategyLiveTrading startLiveTrading(StrategyLiveTrading entity); // 开启实盘
    List<StrategyLiveTrading> getAllActiveTrades();               // 获取活跃实盘
    
    List<StrategyLiveTrading> getByStrategyId(Long stgId);
    StrategyLiveTrading getByStrategyId(Long stgId, String stgRunCd);
    StrategyLiveTrading getById(Long id);
    
    List<StrategyLiveOrderTrade> getTradesByLiveId(Long id);
    
    void updateHeartbeat(Long id);
}
