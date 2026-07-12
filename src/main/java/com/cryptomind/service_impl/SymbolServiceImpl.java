package com.cryptomind.service_impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cryptomind.dao.CmCoinTypeDao;
import com.cryptomind.dao.CmSymbolDao;
import com.cryptomind.entity.CmCoinType;
import com.cryptomind.entity.CmSymbol;
import com.cryptomind.service.SymbolService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SymbolServiceImpl implements SymbolService{

	@Resource
	private CmCoinTypeDao cmCoinTypeDao;
	
	
	@Resource
	private CmSymbolDao symbolDao;
	
	@Override
	public List<CmSymbol> findSymbolList() {
		return symbolDao.findAll();
	}

	@Override
	public List<CmCoinType> findCoinTypeList() {
		return cmCoinTypeDao.findAll();
	}

	@Override
	public CmSymbol findBySymbol(String symbol) {
		return symbolDao.findBySymbol(symbol);
	}

}
