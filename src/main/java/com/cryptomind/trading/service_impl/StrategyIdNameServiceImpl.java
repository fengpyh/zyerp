package com.cryptomind.trading.service_impl;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cryptomind.dao.StrategyIdNameDao;
import com.cryptomind.entity.StrategyIdName;
import com.cryptomind.trading.service.StrategyIdNameService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StrategyIdNameServiceImpl implements StrategyIdNameService{
	
	@Resource
	private StrategyIdNameDao strategyIdNameDao;
	
	@Override
	public StrategyIdName saveOrUpdate(StrategyIdName policy) {
		if(policy.getId()!=null && policy.getId()>0L) {
			StrategyIdName dbId = getById(policy.getId());
			//dbId.set
			
			return strategyIdNameDao.save(policy);
		}else {
			return strategyIdNameDao.save(policy);
		}
	}

	@Override
	public StrategyIdName getById(Long id) {
		Optional<StrategyIdName> opt = strategyIdNameDao.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
	@Override
	public StrategyIdName getByCode(String code) {
		Optional<StrategyIdName> opt = strategyIdNameDao.findByCode(code);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	

	@Override
	public List<StrategyIdName> listAll() {
		return strategyIdNameDao.findAll();
	}

}
