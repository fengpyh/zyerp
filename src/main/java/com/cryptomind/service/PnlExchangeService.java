package com.cryptomind.service;

import java.util.List;

import com.cryptomind.entity.PnlExchange;

public interface PnlExchangeService {

	PnlExchange saveUpdate(PnlExchange exhg);
	
	List<PnlExchange> findAll();
}
