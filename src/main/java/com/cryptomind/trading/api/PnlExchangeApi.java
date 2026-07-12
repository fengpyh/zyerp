package com.cryptomind.trading.api;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cryptomind.entity.PnlExchange;
import com.cryptomind.service.PnlExchangeService;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PnlExchangeApi {

    
    private Gson gson = new Gson();
	
	@Resource
	private PnlExchangeService pnlExchangeService;

    @PostMapping("/api/pnl/exchange/save_update")
    @ResponseBody
    public ResponseEntity<PnlExchange> apiQuoteCandles(@RequestBody PnlExchange exhg) {

    	
    	PnlExchange p = pnlExchangeService.saveUpdate(exhg);
    	
        return ResponseEntity.ok(p);

    }
    
}
