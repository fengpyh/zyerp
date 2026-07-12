package com.cryptomind.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cryptomind.entity.StrategyBacktestResult;

public interface StrategyBacktestResultDao  extends JpaRepository<StrategyBacktestResult, Long> {

	@Query(value = "select * from stg_backtest where strategy_id= :strategy_id order by id desc",nativeQuery = true)
	List<StrategyBacktestResult> getByStrategyId(@Param("strategy_id") String strategy_id);
}
