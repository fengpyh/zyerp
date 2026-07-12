package com.cryptomind.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cryptomind.entity.StrategyIdName;

public interface StrategyIdNameDao  extends JpaRepository<StrategyIdName, Long> {

	// 允许通过策略编码快速索引，方便规则引擎加载
    Optional<StrategyIdName> findByCode(String code);
}
