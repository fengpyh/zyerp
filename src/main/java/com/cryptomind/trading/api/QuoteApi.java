package com.cryptomind.trading.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cryptomind.entity.CmSymbol;
import com.cryptomind.service.SymbolService;
import com.cryptomind.trading.dto.KlineItem;
import com.cryptomind.trading.dto.SymbolData;
import com.cryptomind.trading.utils.BigDecimalUtils;
import com.cryptomind.trading.utils.HttpUtil;
import com.google.gson.Gson;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@RestController
public class QuoteApi {

    
    private Gson gson = new Gson();
	
	@Resource
	private SymbolService symbolService;


	/*
	 * okx format
	 * {
    "code":"0",
    "msg":"",
    "data":[
     [
        "1597026383085",
        "3.721",
        "3.743",
        "3.677",
        "3.708",
        "8422410",
        "22698348.04828491",
        "12698348.04828491",
        "0"
    ],
    [
        "1597026383085",
        "3.731",
        "3.799",
        "3.494",
        "3.72",
        "24912403",
        "67632347.24399722",
        "37632347.24399722",
        "1"
    ]
    ]
}
	 * 
	 * 
	 */
    @GetMapping("/api/quote/candles")
    @ResponseBody
    public ResponseEntity<List<BigDecimal[]>> apiQuoteCandles(@RequestParam("symbol") String symbol, @RequestParam("step") Integer step) {

        //CmSymbol symbolData = symbolService.findBySymbol(symbol);
        //List<KlineItem> klineList = quoteV3Service.getApiQuoteV3Candles(symbolData.getSymbol(), from, to, 1);
        String path= "/api/v5/market/candles?bar=1m&limit=300&instId="+symbol;
        String endpoint = "https://www.okx.com";
        String url = String.format("%s%s", endpoint, path);
        String content = HttpUtil.httpGet(url);
        OkxCandle candle = gson.fromJson(content, OkxCandle.class);
        List<BigDecimal[]> returnList = new ArrayList<> (candle.getData().size());
        for(String[] item: candle.getData()) {

            BigDecimal[] a =new BigDecimal[6];
            a[0] = new BigDecimal(item[0]).divide(new BigDecimal(1)).setScale(0, RoundingMode.DOWN);
            a[1] = BigDecimalUtils.scale(new BigDecimal(item[1]), 4);
            a[2] = BigDecimalUtils.scale(new BigDecimal(item[2]), 4);
            a[3] = BigDecimalUtils.scale(new BigDecimal(item[3]), 4);
            a[4] = BigDecimalUtils.scale(new BigDecimal(item[4]), 4);
            a[5] = BigDecimalUtils.scale(new BigDecimal(item[5]), 4);

            if(a[1].compareTo(BigDecimal.ONE)==0 || a[2].compareTo(BigDecimal.ONE)==0  || a[3].compareTo(BigDecimal.ONE)==0  || a[4].compareTo(BigDecimal.ONE)==0
                    || a[5].compareTo(BigDecimal.ZERO)==0) {
                continue;
            }

            returnList.add(a);
        }

        Collections.sort(returnList, new Comparator<BigDecimal[]>() {
            @Override
            public int compare(BigDecimal[] o1, BigDecimal[] o2) {
                return o1[0].compareTo(o2[0]);
            }});
        
        return ResponseEntity.ok(returnList);

    }
    
    
    //@GetMapping("/api/quote/ticker", produces = "application/json")
    @RequestMapping(value="/api/quote/ticker", method=RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> apiQuoteTicker(@RequestParam("symbol") String symbol) {

        //CmSymbol symbolData = symbolService.findBySymbol(symbol);
        //List<KlineItem> klineList = quoteV3Service.getApiQuoteV3Candles(symbolData.getSymbol(), from, to, 1);
        String path= "/api/v5/market/ticker?instId="+symbol;
        String endpoint = "https://www.okx.com";
        String url = String.format("%s%s", endpoint, path);
        String content = HttpUtil.httpGet(url);
        return ResponseEntity.ok(content);

    }
    
    
    @RequestMapping(value="/api/quote/book100", method=RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> apiQuoteBook100(@RequestParam("symbol") String symbol,@RequestParam("sz") Integer size) {
        String path= "/api/v5/market/books?instId="+symbol + "&sz="+size;
        String endpoint = "https://www.okx.com";
        String url = String.format("%s%s", endpoint, path);
        String content = HttpUtil.httpGet(url);
        return ResponseEntity.ok(content);
    }
    
    
    @RequestMapping(value="/api/quote/trades", method=RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<String> apiQuoteTrades(@RequestParam("symbol") String symbol,@RequestParam("sz") Integer size) {
        String path= "/api/v5/market/trades?instId="+symbol + "&limit="+size;
        String endpoint = "https://www.okx.com";
        String url = String.format("%s%s", endpoint, path);
        String content = HttpUtil.httpGet(url);
        return ResponseEntity.ok(content);
    }
    
    
    
    @GetMapping("/api/quote/candles2")
    @ResponseBody
    public ResponseEntity<List<BigDecimal[]>> apiQuoteCandles2(@RequestParam("symbol") String symbol, @RequestParam("step") Integer step) {

        Long to = System.currentTimeMillis()/1000;
        Long from = to-60*300;

        CmSymbol symbolData = symbolService.findBySymbol(symbol);

        //List<KlineItem> klineList = quoteV3Service.getApiQuoteV3Candles(symbolData.getSymbol(), from, to, 1);
        List<KlineItem> klineList = null;
        String path= "/api/v5/market/candles?instId="+symbol;
        String endpoint = "https://www.okx.com";
        String url = String.format("%s%s", endpoint, path);
        //String content = HttpUtil.httpGet(url);
        if(klineList==null) {
            klineList = new ArrayList<>(0);
        }
        List<BigDecimal[]> returnList = new ArrayList<> (klineList.size());
        /*
        if(klineList==null) {
            klineList = new ArrayList<>(0);
        }
        log.info("size: {}", klineList.size());
        List<BigDecimal[]> returnList = new ArrayList<> (klineList.size());
        for(KlineItem item: klineList) {

            BigDecimal[] a =new BigDecimal[6];
            a[0] = new BigDecimal(item.getOt()).setScale(0, RoundingMode.DOWN);
            a[1] = BigDecimalUtils.scale(new BigDecimal(item.getO()), 4);
            a[2] = BigDecimalUtils.scale(new BigDecimal(item.getH()), 4);
            a[3] = BigDecimalUtils.scale(new BigDecimal(item.getL()), 4);
            a[4] = BigDecimalUtils.scale(new BigDecimal(item.getC()), 4);
            a[5] = BigDecimalUtils.scale(new BigDecimal(item.getV()), 4);

            if(a[1].compareTo(BigDecimal.ONE)==0 || a[2].compareTo(BigDecimal.ONE)==0  || a[3].compareTo(BigDecimal.ONE)==0  || a[4].compareTo(BigDecimal.ONE)==0
                    || a[5].compareTo(BigDecimal.ZERO)==0) {
                continue;
            }

            returnList.add(a);
        }

        Collections.sort(returnList, new Comparator<BigDecimal[]>() {
            @Override
            public int compare(BigDecimal[] o1, BigDecimal[] o2) {
                return o1[0].compareTo(o2[0]);
            }});
		*/
        return ResponseEntity.ok(returnList);

    }
}
