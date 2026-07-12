package com.cryptomind.service;

import java.util.List;

import com.cryptomind.entity.CmCoinType;
import com.cryptomind.entity.CmSymbol;

public interface SymbolService {
	List<CmCoinType> findCoinTypeList();
	List<CmSymbol> findSymbolList();
	
	CmSymbol findBySymbol(String symbol);
}
