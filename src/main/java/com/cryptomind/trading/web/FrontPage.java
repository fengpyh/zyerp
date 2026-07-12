package com.cryptomind.trading.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cryptomind.dto.StrategyMetricDTO;
import com.cryptomind.dto.StrategyPositionDTO;
import com.cryptomind.entity.CmCoinType;
import com.cryptomind.entity.CmSymbol;
import com.cryptomind.entity.PnlExchange;
import com.cryptomind.entity.StrategyBacktestResult;
import com.cryptomind.entity.StrategyIdName;
import com.cryptomind.entity.StrategyLiveOrderTrade;
import com.cryptomind.entity.StrategyLiveTrading;
import com.cryptomind.service.PnlExchangeService;
import com.cryptomind.service.SymbolService;
import com.cryptomind.trading.api.BaseApi;
import com.cryptomind.trading.dto.*;
import com.cryptomind.trading.service.LiveTradingService;
import com.cryptomind.trading.service.StrategyBacktestResultService;
import com.cryptomind.trading.service.StrategyIdNameService;
import com.cryptomind.trading.utils.BigDecimalUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Controller
public class FrontPage extends BaseApi {

	@Resource
	private HttpServletRequest request;

	@GetMapping("/")
    public String index(ModelMap model) {
        model.addAttribute("pageId", "dashboard");
        model.addAttribute("pageTitle", "page.dashboard.title");
        
        common(model);
        return "dashboard";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(ModelMap model) {
        model.addAttribute("pageId", "dashboard");
        model.addAttribute("pageTitle", "page.dashboard.title");
        common(model);
        return "dashboard";
    }

    @GetMapping("/pvuv")
    public String pvuv(Model model) {
        model.addAttribute("pageId", "pvuv");
        model.addAttribute("pageTitle", "page.pvuv.title");
        return "pvuv";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("pageId", "pvuv");
        model.addAttribute("pageTitle", "page.pvuv.title");
        return "login";
    }
    
	private void common(ModelMap model) {
		Long userId = super.getUserId(request);
		String email = super.getEmail(request);
		model.addAttribute("user_auth", userId>0?"Y":"N");
		model.addAttribute("LG", userId>0);
		model.addAttribute("email", email);
		model.addAttribute("uid", userId);
		
		
	}
}
