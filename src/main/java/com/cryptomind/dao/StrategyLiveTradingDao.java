package com.cryptomind.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cryptomind.entity.StrategyBacktestResult;
import com.cryptomind.entity.StrategyLiveTrading;

public interface StrategyLiveTradingDao  extends JpaRepository<StrategyLiveTrading, Long> {

	@Query(value = "select * from stg_live_trading where stg_id= :stg_id order by id desc",nativeQuery = true)
	List<StrategyLiveTrading> getByStrategyId(@Param("stg_id") Long stg_id);
	
	@Query(value = "select * from stg_live_trading where stg_run_cd= :stg_run_cd order by id desc",nativeQuery = true)
	List<StrategyLiveTrading> getByLiveTradingCd(@Param("stg_run_cd") String stg_run_cd);
	
	@Query(value = "select * from stg_live_trading where stg_id= :stg_id and stg_run_cd=:stg_run_cd order by id desc",nativeQuery = true)
	StrategyLiveTrading getByStrategyIdAndCd(@Param("stg_id") Long stg_id, @Param("stg_run_cd") String stg_run_cd);
	
}
