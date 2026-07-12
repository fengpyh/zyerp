package com.cryptomind.wquote;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


import com.cryptomind.entity.CmSymbol;
import com.cryptomind.trading.utils.ExceptionUtil;
import com.cryptomind.trading.utils.MathUtils;
import com.cryptomind.trading.utils.MoneyUtils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class QuoteData {

	private CmSymbol symbolData;
	
	//deal history
	//private final static int DEAL_HISTORY_SIZE = 100;
	private int dealHistory_currentIndex = 0;
	private DealDetailDTO[] dealHistory = new DealDetailDTO[100];

	//orderbook
	private DepthMsgDTO depthMsgDTO;
	
	//kline, 1minute and daily only
	private Map<Integer, KlineDTO[]> klineMap = new HashMap<>();

	public void setKlineList(Integer key, KlineDTO[] v) {
		klineMap.put(key, v);
	}

	//price
	private H24PriceDTO h24PriceDTO;

	private boolean deal_dirty;
	private boolean depth_dirty;
	//private boolean kline_dirty;
	
	public String getSymbol() {
		return symbolData.getSymbol();
	}


	/*
	private BigDecimal      va;//成交额(V-Amount 按计价货币统计)
    private BigDecimal      vc;//成交数量（V-Count 按交易货币统计）
    private BigDecimal      vu;//USD成交额(V-USD 按美元计价统计)
    private BigDecimal      cp;//24Hour涨跌幅(Change Percent) 计算公式 :涨跌幅 = （收盘价-开盘价）/收盘价
    private BigDecimal      ca;//24Hour涨跌额(Change Amount)  计算公式 :涨跌幅 = 收盘价-开盘价
	 */
	public void clear() {
		this.h24PriceDTO = H24PriceDTO
                .builder()
                .symbol(symbolData.getSymbol())
				.ca(BigDecimal.ZERO)
				.vu(BigDecimal.ZERO)
				.vc(BigDecimal.ZERO)
				.c(BigDecimal.ZERO)
				.cp(BigDecimal.ZERO)
				.sign("")
				.va(BigDecimal.ZERO)
				.daySeqFrom(0L)
				.dayseqTo(0L)
				.h(BigDecimal.ZERO)
				.l(BigDecimal.ZERO)
				.o(BigDecimal.ZERO)
				.c(BigDecimal.ZERO)
				.time(0L)
                .build();

        //deal
        //DO NOT NEED INIT
        dealHistory = new DealDetailDTO[100];
        
        //orderbook ask/bid 15 level only
        depthMsgDTO = new DepthMsgDTO();
        depthMsgDTO.setTimestamp(0L);
        //depthMsgDTO.setTradeMappingId(symbolData.getSymbolId());
        DepthDTO depth = new DepthDTO();
        depth.setBuyDepth(null);
        depth.setSellDepth(null);
        depthMsgDTO.setDepth(depth);
        
        //kline
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
    	cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        long day_start_timestamp = cal.getTimeInMillis(); //(timestamp/1000L)*1000L-h*3600*1000+m*60*1000+s*1000;
        //log.info("day_start_timestamp={}", day_start_timestamp);
        for(Integer step: KlineStepConstant.STEPS) {
        	quoteData_initKline(step, day_start_timestamp);
        }

		this.deal_dirty=false;
		this.depth_dirty=false;
	}
	
	public QuoteData(CmSymbol symbolData) {
		this.symbolData = symbolData;
		clear();
	}

	private void quoteData_initKline(int step, long day_start_timestamp) {
		KlineDTO[] list = klineMap.get(step);
		KlineStep stepVal = KlineStep.getByMinStep(step);
		//long ot = 0L;
		//long ct = 0L;
		//int len = 1;
		if(step==KlineStepConstant.STEP_1_WEEK) {
			list = new KlineDTO[1];
			klineMap.put(step, list);
			
			Calendar cal = Calendar.getInstance();
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	        cal.add(Calendar.DAY_OF_MONTH, 2-dayOfWeek); //Tues=3, Sunday=1, Monday=2
	        cal.set(Calendar.HOUR_OF_DAY,0);
	    	cal.set(Calendar.MINUTE,0);
	        cal.set(Calendar.SECOND,0);
	        cal.set(Calendar.MILLISECOND,0);
			long start = cal.getTimeInMillis();
			long ot = start;
			long ct = start + (24L*3600L*1000L)*7L-1000;
			//log.info("param: {},{},{},{}, val: {}, {}, {}",step, id, name, day_start_timestamp, stepVal, ot,ct);



			KlineDTO k = KlineDTO.builder()
					.symbol(symbolData.getSymbol())
                    .step(stepVal)
                    .h("")
                    .o("")
                    .l("")
                    .c("")
                    .v("")
                    .prev_c("")
                    .vol_ccy("")
                    .vol_ccy_quote("")
					.ot(ot)
                    .ct(ct)
					.ts(ot)
					.build();
			list[0]= k;
		}else if(step==KlineStepConstant.STEP_1_MONTH) {
			list = new KlineDTO[1];
			klineMap.put(step, list);
			
			Calendar cal = Calendar.getInstance();
			int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
			int month = cal.get(Calendar.MONTH)+1;
	        cal.add(Calendar.DAY_OF_MONTH, 1-dayOfMonth); //dayOfMonth start from 1
	        cal.set(Calendar.HOUR_OF_DAY,0);
	    	cal.set(Calendar.MINUTE,0);
	        cal.set(Calendar.SECOND,0);
	        cal.set(Calendar.MILLISECOND,0);
			long start = cal.getTimeInMillis();
			long ot = start;
			long ct=0L;
			if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) {
				ct = start + (24L*3600L*1000L)*31L-1000;
			}else if(month==4 || month==6 || month==9 || month==11) {
				ct = start + (24L*3600L*1000L)*30L-1000;
			} else {
				//2020,2016
				int year = cal.get(Calendar.YEAR);
				if( (year % 4 == 0 && year % 100 != 0) || year % 400 == 0 ){
					ct = start + (24L*3600L*1000L)*29L-1000;
	            }else {
	            	ct = start + (24L*3600L*1000L)*28L-1000;
	            }
			}
			//log.info("param: {},{},{},{}, val: {}, {}, {}",step, id, name, day_start_timestamp, stepVal, ot,ct);
			KlineDTO k = KlineDTO.builder()
					.symbol(symbolData.getSymbol())
                    .step(stepVal)
                    .h("")
                    .o("")
                    .l("")
                    .c("")
                    .v("")
                    .prev_c("")
                    .vol_ccy("")
                    .vol_ccy_quote("")
					.ot(ot)
                    .ct(ct)
					.ts(ot)
					.build();
			
			list[0]= k;
		}else {
			int len=1440/step+2;
			if(list==null) {
				list = new KlineDTO[len];
				klineMap.put(step, list);
			}
			
			for(int i=0;i<len;i++) {
				long ot = day_start_timestamp+i*step*60L*1000L;
				long ct = day_start_timestamp+(i+1)*step*60L*1000L-1L;
				//log.info("param: {},{},{},{}, val: {}, {}, {}",step, id, name, day_start_timestamp, stepVal, ot,ct);
				KlineDTO k = KlineDTO.builder()
						.symbol(symbolData.getSymbol())
	                    .step(stepVal)
	                    .h("")
	                    .o("")
	                    .l("")
	                    .c("")
	                    .v("")
	                    .prev_c("")
	                    .vol_ccy("")
	                    .vol_ccy_quote("")
						.ot(ot)
	                    .ct(ct)
						.ts(ot)
						.build();
				list[i]= k;
			}
		}
	}
	
	public void handleDepth(DepthMsgDTO param) {
		if(param==null || param.getTimestamp()==null) {
			
			log.info("param null error, {}", param);
			return;
		}
		
		if(this.depthMsgDTO==null || this.depthMsgDTO.getTimestamp()==null || param.getTimestamp()> this.depthMsgDTO.getTimestamp()   ) {
			this.depthMsgDTO = param;
			this.depth_dirty = true;
		}
	}

	
	public DealDetailDTO getCurrentLatestDeal() {
		DealDetailDTO d = this.dealHistory[dealHistory_currentIndex];
		if(d==null) {
			for(int i=dealHistory_currentIndex;i>=0;i--) {
				if(this.dealHistory[i]!=null) {
					return this.dealHistory[i];
				}
			}
			
			for(int i=dealHistory.length-1;i>=0;i--) {
				if(this.dealHistory[i]!=null) {
					return this.dealHistory[i];
				}
			}
		}
		return null;
	}

	public void handleDeal(DealDetailMsgDTO dealDetailMsgDTO) {
		try {
			//long start = System.currentTimeMillis();
			List<DealDetailDTO> dealList = dealDetailMsgDTO.getDealList();
			DealDetailDTO dealDetailDTO = null;
			if (dealList != null && !dealList.isEmpty()) {
				dealDetailDTO = dealList.get(0);
				dealHistory[dealHistory_currentIndex] = dealDetailDTO;
				dealHistory_currentIndex = dealHistory_currentIndex + 1;
				if (dealHistory_currentIndex >= dealHistory.length) {
					dealHistory_currentIndex = 0;
				}
			}

			if (dealDetailDTO != null) {
				BigDecimal amount = h24PriceDTO.getVa();
				BigDecimal count = h24PriceDTO.getVc();

				//amount = amount.add(dealDetailDTO.getDealAmount());
				count = count.add(dealDetailDTO.getDealCount());

				if (h24PriceDTO.getO() == null || h24PriceDTO.getO().compareTo(BigDecimal.ZERO) <= 0) {
					h24PriceDTO.setO(dealDetailDTO.getDealPrice());
				}

				if (h24PriceDTO.getL() == null || h24PriceDTO.getL().compareTo(BigDecimal.ZERO) <= 0) {
					h24PriceDTO.setL(dealDetailDTO.getDealPrice());
				}

				h24PriceDTO.setH(MathUtils.max(h24PriceDTO.getH(), dealDetailDTO.getDealPrice()));
				h24PriceDTO.setL(MathUtils.max(h24PriceDTO.getL(), dealDetailDTO.getDealPrice()));
				h24PriceDTO.setC(dealDetailDTO.getDealPrice());

				//h24PriceDTO.setVu(dealDetailDTO.getDealAmount());
				h24PriceDTO.setVa(MoneyUtils.scaleByPrecision(amount, symbolData.getPrice_scale()));
				h24PriceDTO.setVc(MoneyUtils.scaleByPrecision(count, symbolData.getCount_scale()));

				//h24PriceDTO.setPrice(h24PriceDTO.getC().multiply(rate));
				//涨跌额 收盘价-开盘价
				h24PriceDTO.setCa(MoneyUtils.scaleByPrecision(h24PriceDTO.getC().subtract(h24PriceDTO.getO()).setScale(8, RoundingMode.HALF_UP), symbolData.getPrice_scale()));
				//涨跌幅 （收盘价-开盘价）/开盘价
				BigDecimal pct_val = MathUtils.computeCp(h24PriceDTO.getC(), h24PriceDTO.getO());
				pct_val = pct_val.multiply(new BigDecimal(100)).setScale(2, RoundingMode.DOWN);
				h24PriceDTO.setCp(pct_val);

				h24PriceDTO.setTime(dealDetailDTO.getDealTime());
				//h24PriceDTO.setTradeSeq(System.currentTimeMillis());
				//h24PriceDTO.setDayseqTo(System.currentTimeMillis());
				//price END
			}

			handleDeal_Kline(dealDetailMsgDTO);
			//long end = System.currentTimeMillis();
			//long mills = end-start;
			//log.info("handleDeal, time={} mills", end-start);

			this.deal_dirty = true;

		}catch (Exception e) {
			log.error("quoteData.handleDeal: {}", ExceptionUtil.toString(e));
		}
	}
	
	private void handleDeal_Kline(DealDetailMsgDTO dealDetailMsgDTO) {
		//long start = System.currentTimeMillis();
		for(Integer step: KlineStepConstant.STEPS) {
			handleDeal_Kline_step(step, dealDetailMsgDTO);
		}
		//long end = System.currentTimeMillis();
        //log.info("handleDeal.kline, steps_len={}, time={} mills",KlineStepConstant.STEPS.length, end-start);
	}
	
	private void handleDeal_Kline_step(Integer step, DealDetailMsgDTO dealDetailMsgDTO) {
		KlineDTO[] list = klineMap.get(step);
		int len=1440/step+2;
		int i = handleDeal_Kline_step_calcIndex(step, dealDetailMsgDTO.getTime());
		//log.info("setp, time, index= {}, {}, {}", step, dealDetailMsgDTO.getTime(), i);
		if(i>=0 && i<len  ) {
			KlineDTO kline = list[i];
			for(DealDetailDTO dto:dealDetailMsgDTO.getDealList() ) {
				handleDeal_Kline_step_updateKline(kline, dto);
			}
		}else {
			log.error("time={}, index={} outOfRange {}", dealDetailMsgDTO.getTime(), i, len);
		}
	}
	
	private int handleDeal_Kline_step_calcIndex(Integer step, Long timestamp) {
		int i = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp);
		int h =cal.get(Calendar.HOUR_OF_DAY);
		int m =cal.get(Calendar.MINUTE);
		switch(step) {
		case KlineStepConstant.STEP_1_MINUTE:
			i=h*60+m; break;
		case KlineStepConstant.STEP_5_MINUTE:
			i=(h*60+m)/5; break;
		case KlineStepConstant.STEP_15_MINUTE:
			i=(h*60+m)/15; break;
		case KlineStepConstant.STEP_30_MINUTE:
			i=(h*60+m)/30; break;
		case KlineStepConstant.STEP_1_HOUR:
			i=h; break;
		case KlineStepConstant.STEP_2_HOUR:
			i=h/2; break;
		case KlineStepConstant.STEP_4_HOUR:
			i=h/4; break;
		case KlineStepConstant.STEP_1_DAY:
			i=0; break;
		case KlineStepConstant.STEP_1_WEEK:
			i=0; break;
		case KlineStepConstant.STEP_1_MONTH:
			i=0; break;
		default:
			i=0; break;
		}
		return i;
	}
	
	private void handleDeal_Kline_step_updateKline(KlineDTO kline, DealDetailDTO dealDetailDTO) {
		
		Long time = dealDetailDTO.getDealTime();
		
		BigDecimal price = dealDetailDTO.getDealPrice();
		
		if(price.compareTo(BigDecimal.ZERO)<=0) {
			log.error("error.price {}, {}", price, dealDetailDTO);
			return;
		}
		String c = price.toString();
		BigDecimal count = dealDetailDTO.getDealCount();
		
		BigDecimal amount =price.multiply(count).setScale(symbolData.getPrice_scale(), RoundingMode.DOWN);
		
		if(kline.getOt()<=time ) {
			if(kline.getTs()==null || kline.getTs()<time) {
				kline.setC(c);
			}
			kline.setTs(time);
		}
		
		if(kline.getC()==null || price.compareTo(BigDecimal.ZERO)<=0 ) {
			kline.setC(c);
		}

		if(kline.getO()==null || kline.getO().isEmpty()) {
			kline.setO(c);
		}
		
		if(kline.getH()==null || kline.getH().isEmpty() || new BigDecimal(kline.getH()).compareTo(price)<0) {
			kline.setH(c);
		}
		
		if(kline.getL()==null || kline.getL().isEmpty() || new BigDecimal(kline.getL()).compareTo(price)>0) {
			if(price.compareTo(BigDecimal.ZERO)>0) {
				kline.setL(c);
			}
		}
		BigDecimal newV = new BigDecimal(kline.getV()).add(count).setScale(symbolData.getCount_scale(), RoundingMode.DOWN);
		BigDecimal newVa = new BigDecimal(kline.getVol_ccy()).add(amount).setScale(symbolData.getPrice_scale(), RoundingMode.DOWN);
		kline.setV(newV.toString());
		kline.setVol_ccy(newVa.toString());
		kline.setVol_ccy_quote(newVa.toString());
	}

}
