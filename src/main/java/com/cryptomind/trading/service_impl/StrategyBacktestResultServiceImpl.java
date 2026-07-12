package com.cryptomind.trading.service_impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cryptomind.dao.StrategyBacktestResultDao;
import com.cryptomind.entity.StrategyBacktestResult;
import com.cryptomind.trading.service.StrategyBacktestResultService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class StrategyBacktestResultServiceImpl implements StrategyBacktestResultService{

	@Resource
	private StrategyBacktestResultDao strategyBacktestResultDao;
	
	@Override
	public StrategyBacktestResult saveResult(StrategyBacktestResult entity) {
		return strategyBacktestResultDao.save(entity);
	}

	@Override
	public List<StrategyBacktestResult> getByStrategyId(String strategyId) {
		return strategyBacktestResultDao.getByStrategyId(strategyId);
	}

	@Override
	public List<StrategyBacktestResult> getTopPerformers(String strategyId, int page, int size) {
		return strategyBacktestResultDao.findAll();
	}

	@Override
	public StrategyBacktestResult getById(Long id) {
		return strategyBacktestResultDao.getOne(id);
	}

}
