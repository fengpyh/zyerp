package com.cryptomind.trading.service;

import java.util.List;

import com.cryptomind.entity.StrategyIdName;

public interface StrategyIdNameService {

	StrategyIdName saveOrUpdate(StrategyIdName policy);
	StrategyIdName getById(Long id);
	StrategyIdName getByCode(String code);
    List<StrategyIdName> listAll();
}
