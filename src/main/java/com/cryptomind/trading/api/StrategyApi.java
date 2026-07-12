package com.cryptomind.trading.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cryptomind.entity.StrategyIdName;
import com.cryptomind.entity.StrategyLiveTrading;
import com.cryptomind.trading.service.LiveTradingService;
import com.cryptomind.trading.service.StrategyIdNameService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/stg")
public class StrategyApi extends BaseApi {

	
	@Autowired
	HttpServletRequest request;
	
	@Resource
	private StrategyIdNameService strategyIdNameService;
	
	@Resource
	private LiveTradingService liveTradingService;
	
	
    /**
     * 创建或更新策略
     * 接收 Bootstrap 表单异步发来的 JSON 载荷
     */
    @PostMapping("/save_update")
    public ResponseEntity<StrategyIdName> savePolicy(@RequestBody StrategyIdName policy) {
        try {
        	StrategyIdName savedPolicy = strategyIdNameService.saveOrUpdate(policy);
            return ResponseEntity.ok(savedPolicy);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * 根据 ID 查询单条策略，用于编辑回显
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<StrategyIdName> getPolicy(@PathVariable Long id) {
        return ResponseEntity.ok(strategyIdNameService.getById(id));
    }
    
    
    // 2. 接收表单提交数据并保存
    @PostMapping("/start_live")
    public ResponseEntity<StrategyLiveTrading> startLive(@RequestBody StrategyLiveTrading entity) {
    	StrategyLiveTrading dbLive = liveTradingService.startLiveTrading(entity);
    	return ResponseEntity.ok(dbLive);
    }
    
    @PostMapping("/update_start_live")
    public ResponseEntity<StrategyLiveTrading> updateStartLive(@RequestBody StrategyLiveTrading entity) {
    	StrategyLiveTrading dbLive = liveTradingService.startLiveTrading(entity);
    	return ResponseEntity.ok(dbLive);
    }
    
    @GetMapping("/get_live_stg")
    public ResponseEntity<StrategyLiveTrading> getLiveStg(@RequestParam("stgid") Long stgId, @RequestParam("runCd") String runCd) {
    	StrategyLiveTrading dbLive = liveTradingService.getByStrategyId(stgId, runCd);
    	return ResponseEntity.ok(dbLive);
    }
    
    @GetMapping("/get_live_stg_by_id")
    public ResponseEntity<StrategyLiveTrading> getLiveStg(@RequestParam("id") Long stgId) {
    	StrategyLiveTrading dbLive = liveTradingService.getById(stgId);
    	return ResponseEntity.ok(dbLive);
    }
}
