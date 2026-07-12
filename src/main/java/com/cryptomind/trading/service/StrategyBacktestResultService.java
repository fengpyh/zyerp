package com.cryptomind.trading.service;

import java.util.List;

import com.cryptomind.entity.StrategyBacktestResult;

public interface StrategyBacktestResultService {
	StrategyBacktestResult saveResult(StrategyBacktestResult entity);
    List<StrategyBacktestResult> getByStrategyId(String strategyId);
    List<StrategyBacktestResult> getTopPerformers(String strategyId, int page, int size);
    StrategyBacktestResult getById(Long id);
    
}
