package com.cryptomind.trading.service_impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cryptomind.dao.StrategyLiveOrderTradeDao;
import com.cryptomind.dao.StrategyLiveTradingDao;
import com.cryptomind.entity.StrategyLiveOrderTrade;
import com.cryptomind.entity.StrategyLiveTrading;
import com.cryptomind.trading.service.LiveTradingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LiveTradingServiceImpl implements LiveTradingService{

	@Resource
	private StrategyLiveTradingDao strategyLiveTradingDao;
	
	@Resource
	private StrategyLiveOrderTradeDao strategyLiveOrderTradeDao;
	
	@Override
	public StrategyLiveTrading startLiveTrading(StrategyLiveTrading entity) {
		// TODO Auto-generated method stub
		return strategyLiveTradingDao.save(entity);
	}

	@Override
	public List<StrategyLiveTrading> getAllActiveTrades() {
		// TODO Auto-generated method stub
		return strategyLiveTradingDao.findAll();
	}
	
	@Override
	public List<StrategyLiveTrading> getByStrategyId(Long stgId) {
		return strategyLiveTradingDao.getByStrategyId(stgId);
	}
    
	@Override
	public StrategyLiveTrading getByStrategyId(Long stgId, String stgRunCd) {
		return strategyLiveTradingDao.getByStrategyIdAndCd(stgId, stgRunCd);
	}
    
	@Override
	public StrategyLiveTrading getById(Long id) {
		 return strategyLiveTradingDao.getOne(id);
	}

	@Override
	public void updateHeartbeat(Long id) {
		strategyLiveTradingDao.findById(id).ifPresent(entity -> {
            entity.setLastHeartbeat(LocalDateTime.now());
            strategyLiveTradingDao.save(entity);
        });
	}
	
	
	@Override
	public List<StrategyLiveOrderTrade> getTradesByLiveId(Long id) {
		List<StrategyLiveOrderTrade> trades = strategyLiveOrderTradeDao.findByStgLiveIdOrderByExchangeUpdateTimeDesc(id);
		return trades;
	}

}
