package com.cryptomind.wquote;

import java.util.ArrayList;
import java.util.List;


import lombok.extern.slf4j.Slf4j;

import com.cryptomind.entity.CmSymbol;

@Slf4j
public class QuoteInstance {

	private QuoteInstance() {}
	private static QuoteInstance inst = new QuoteInstance();
	public static QuoteInstance getInstance() {
		return inst;
	}

	//TODO later change to trieTree
	//private Map<String, QuoteData> quoteMap = new HashMap<>();
	
	
	
	/*
	 * cd = symbol, sample: BTC/USDT
	 * symbol = BTC/USDT
	 * 
	 * 
	 */
	public QuoteData addNode(CmSymbol symbolData) {
		String key = symbolData.getSymbol();

		QuoteData data = TrieTree.getInstance().dfs_find(key);
		if(data==null) {
			data = new QuoteData(symbolData);
			//quoteMap.put(cd, data);
			TrieTree.getInstance().insertNode(key, data);
		}
		return data;
	}

	public QuoteData dfsFind(String symbol) {
		return TrieTree.getInstance().dfs_find(symbol);
	}

	private List<QuoteData> dfs(String prefixString) {
		List<QuoteData> list = TrieTree.getInstance().dfsListData(prefixString);
		//List<QuoteData> list = new ArrayList<>(quoteMap.values());
		log.info("dfs.listSize={}", list.size());
		return list;
	}
	
	public List<H24PriceDTO> dfsTickers(String prefixString) {
		List<H24PriceDTO> tickers = new ArrayList<>();
		List<QuoteData> list = dfs(prefixString);
		//int i=0;
		for(QuoteData data: list )  {
			if(data==null) {
				continue;
			}
			if(data.getSymbol()==null) {
				log.warn("getSymbol NULL, for {}, {}", data.getSymbol(), data.getH24PriceDTO());
				continue;
			}
			
			if(data.getH24PriceDTO()==null || data.getH24PriceDTO().getC()==null) {
				log.warn("price NULL, for {}, {}", data.getSymbol(), data.getH24PriceDTO());
				continue;
			}

			tickers.add(data.getH24PriceDTO());
		}
		return tickers;
	}
}
