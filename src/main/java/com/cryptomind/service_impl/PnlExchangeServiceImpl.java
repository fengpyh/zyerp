package com.cryptomind.service_impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cryptomind.dao.PnlExchagneDao;
import com.cryptomind.entity.PnlExchange;
import com.cryptomind.service.PnlExchangeService;
import com.cryptomind.trading.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PnlExchangeServiceImpl implements PnlExchangeService {

	@Resource
	private PnlExchagneDao pnlExchagneDao;
	
	@Override
	public PnlExchange saveUpdate(PnlExchange exhg) {
		// TODO Auto-generated method stub
		
		exhg.setCreate_dt(Utils.getTimestamp());
		exhg.setUpd_dt(Utils.getTimestamp());
		return pnlExchagneDao.save(exhg);
		
	}

	@Override
	public List<PnlExchange> findAll() {
		// TODO Auto-generated method stub
		return pnlExchagneDao.findAll();
	}

}
