package com.cryptomind.ymatch;

import com.cryptomind.dao.CmCoinTypeDao;
import com.cryptomind.dao.CmSymbolDao;
import com.cryptomind.dto.*;
import com.cryptomind.entity.*;
import com.cryptomind.trading.utils.BigDecimalUtils;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
public class TakerServiceImpl implements TakerService {

	private final static int MAX_SEQ=Integer.MAX_VALUE-100;
    private final static long MAX_LONG_SEQ = Long.MAX_VALUE - 100L;
    
	private static int matchSeq =1;
	private static int cancelSeq =1;

    private long matchLongSeq =1l;
    
    private Gson gson = new Gson();


    @Resource
    private MakerService makerService;

    @Resource
    private CmCoinTypeDao cmCoinTypeDao;

    @Resource
    private CmSymbolDao cmSymbolDao;
    
    
    private Map<String, CmCoinType> coinTypeMap = new HashMap<>();
    private Map<String, CmSymbol> symbolMap = new HashMap<>();
    
    @Resource
    private TradeMsgServiceImpl tradeMsgServiceImpl;

    @Resource
    private QuoteMsgServiceImpl quoteMsgService;


    
    @Override
    public void _reload() {
        makerService.clear();
        
        List<CmCoinType> coinTypeList = cmCoinTypeDao.findAll();
        List<CmSymbol> symbolList = cmSymbolDao.findAll();
        for(CmCoinType c: coinTypeList) {
        	coinTypeMap.put(c.getSymbol(), c);
        }
        for(CmSymbol s: symbolList) {
        	/*
        	CmCoinType base = coinTypeMap.get(s.getBase_ccy());
        	if(base==null) {
        		log.error("base CoinType {} Missing, {}", s.getBase_ccy(), s );
        		continue;
        	}
        	CmCoinType quote = coinTypeMap.get(s.getBase_ccy());
        	if(quote==null) {
        		log.error("quote CoinType {} Missing, {}", s.getQuote_ccy(), s );
        		continue;
        	}
        	*/
        	symbolMap.put(s.getSymbol(), s);
        }

    }

    @Override
    public List<TradeDetail> processOrder(OrderDto order) {

            List<TradeDetail> matchDtoList = processOrder_next(order);

            CmSymbol symbol = symbolMap.get(order.getSymbol());
            quoteMsgService.tradeMsgSend(symbol, matchDtoList);
            quoteMsgService.orderBookMsgSend(symbol);
            if(matchDtoList.size()>0) {
            	tradeMsgServiceImpl.publishDeal(gson.toJson(matchDtoList));
            }

            try {
                //
                long s = System.currentTimeMillis();
                String cd = order.getSymbol();
                if (makerService.isMakerPriceError(cd)) {
                    MarketDepthLogCollection marketDepthLogCollection =
                            new MarketDepthLogCollection(cd,makerService.getDepthCollection(cd),
                                    makerService.getMakerEntrusts(cd, MatchEnum.BUY),
                                    makerService.getMakerEntrusts(cd, MatchEnum.SELL));

                    String depthLog = marketDepthLogCollection.getLog();
                    log.error(" DepthPriceCheckError use:{} ms--:{}", (System.currentTimeMillis()-s), depthLog);

                }
            } catch (Exception e) {
                log.error(" DepthPriceCheckException :{}", e);
            }


            return matchDtoList;
    }

    private List<TradeDetail> processOrder_next(OrderDto order) {

        List<TradeDetail> matchList = null;
        if(order.getOperation().equals(MatchEnum.PLACE) ){
            if (order.getOrder_type().equals("limit")) {
                log.info("limit order: {}, {}", order.getOrder_type(), order);
                matchList = reloadMatch(order);
            }

            try {
                //
                long s = System.currentTimeMillis();
                String cd = order.getSymbol();
                if (makerService.isMakerPriceError(cd)) {
                    MarketDepthLogCollection marketDepthLogCollection =
                            new MarketDepthLogCollection(cd,makerService.getDepthCollection(cd),
                                    makerService.getMakerEntrusts(cd, MatchEnum.BUY),
                                    makerService.getMakerEntrusts(cd, MatchEnum.SELL));

                    String depthLog = marketDepthLogCollection.getLog();
                    log.error(" DepthPriceCheckError use:{} ms--:{}", (System.currentTimeMillis()-s), depthLog);

                }
            } catch (Exception e) {
                log.error(" DepthPriceCheckException :{}", e);
            }
        }
        return matchList;
    }

    @Override
    public List<CancelDetail> processCancel(OrderDto order) {
        List<CancelDetail> cancelList = new ArrayList<>();
        OrderDto cancelledOrder = makerService.removeMaker(order);
        if(cancelledOrder!=null) {
            CancelDetail cancelDetail = new CancelDetail();
            cancelDetail.setAmount(cancelledOrder.getAmount());
            cancelDetail.setCount(cancelledOrder.getCount());
            cancelDetail.setPrice(cancelledOrder.getPrice());
            cancelDetail.setId(cancelledOrder.getId());
            cancelDetail.setSide(cancelledOrder.getSide());
            cancelDetail.setTimestamp(System.currentTimeMillis());
            cancelDetail.setCd(cancelledOrder.getSymbol());
            cancelDetail.setSequence(cancelSeq++);
            if(cancelSeq>MAX_SEQ) { cancelSeq=1; }

            cancelList.add(cancelDetail);
            tradeMsgServiceImpl.publishDeal(gson.toJson(cancelList));
        }else{
            log.error("cancel entrust failed. not find the entrust in maker,entrust_id={}",order.getId());

            CancelDetail cancelDetail = new CancelDetail();
            cancelDetail.setAmount(BigDecimal.ZERO);
            cancelDetail.setCount(BigDecimal.ZERO);
            cancelDetail.setPrice(BigDecimal.ZERO);
            cancelDetail.setId(order.getId());
            cancelDetail.setSide(order.getSide());
            cancelDetail.setTimestamp(System.currentTimeMillis());
            cancelDetail.setCd(order.getSymbol());
            cancelDetail.setSequence(cancelSeq++);
            if(cancelSeq>MAX_SEQ) { cancelSeq=1; }

            cancelList.add(cancelDetail);
            
        }

        CmSymbol symbol = symbolMap.get(order.getSymbol());
        quoteMsgService.orderBookMsgSend(symbol);

        return cancelList;
    }



    private boolean validateOrder(OrderDto e) {
        if(e==null) {
            return false;
        }
        if(e.getOrder_type().equals(OrderConst.ORDER_TYP_LIMIT)) {
        	if(e.getCount()==null || e.getPrice()==null) {
                return false;
            }

            if(e.getCount().compareTo(BigDecimal.ZERO)<=0) {
                return false;
            }

            if(e.getPrice().compareTo(BigDecimal.ZERO)<=0) {
                return false;
            }
        }else {
        	if(e.getSide().equals(OrderConst.BUY)) {
        		if(e.getAmount()==null || e.getAmount().compareTo(BigDecimal.ZERO)<=0) {
        			return false;
        		}
        	}else {
        		if(e.getCount()==null || e.getCount().compareTo(BigDecimal.ZERO)<=0) {
        			return false;
        		}
        	}

        }
        
        if( !(e.getSide().equals("buy") || e.getSide().equals("sell")) ) {
            return false;
        }

        return true;
    }



    private List<TradeDetail> reloadMatch(OrderDto order) {
        List<TradeDetail> matchList = new ArrayList<>();

    	if(!validateOrder(order)) {
            log.error("order_id=[{}]-order validate failed: {}", order.getId(), order);
            return matchList;
        }

    	//if(!order.getOrder_type().equals("limit")) {
        //    log.error("id=[{}]- islimit error", order.getId());
    	//    return matchList;
    	//}


        CmSymbol symbol = symbolMap.get(order.getSymbol());

        int scale = symbol.getPrice_scale() + symbol.getCount_scale();

         BigDecimal takerAmount = order.getAmount();
         BigDecimal takerCount = order.getCount();

         String counterPartType = null;
         if(order.getOrder_type().equals(MatchEnum.BUY)) {
        	 counterPartType = MatchEnum.SELL;
         }else {
        	 counterPartType = MatchEnum.BUY;
         }
         
         while(true) {
        	 if(takerCount.compareTo(BigDecimal.ZERO)< 0) {
            	 break;
             }

        	 OrderDto makerOrder = makerService.getMaker(order.getSymbol(), counterPartType);
             if(makerOrder==null) {
                 break;
             }

             if( (order.getSide().equals("buy") && order.getPrice().compareTo(makerOrder.getPrice()) >=0)  //买入价格>=卖出价格
                     || (order.getSide().equals("sell") && order.getPrice().compareTo(makerOrder.getPrice()) <=0)  ) { //卖出价格<=买入价格

                 //
                 long ts = System.currentTimeMillis();

                 BigDecimal tradeAmount = null;
                 BigDecimal tradeCount = null;
                 BigDecimal tradePrice = makerOrder.getPrice();

                 int ret = takerCount.compareTo(makerOrder.getCount());
            	 if (ret > 0 || ret == 0) {
            		 //taker部分成交or全部成交, maker全部成交
            		 tradeAmount = BigDecimalUtils.mul(makerOrder.getCount(), tradePrice, scale);
            		 tradeCount = makerOrder.getCount();

            		 takerAmount = BigDecimalUtils.sub(takerAmount, tradeAmount, scale);
            		 takerCount = BigDecimalUtils.sub(takerCount, tradeCount, symbol.getCount_scale());
            	 }else {
            		 //taker全部成交, maker部分成交
                     tradeAmount =  BigDecimalUtils.mul(takerCount, tradePrice, scale);
            		 tradeCount = takerCount;

            		 takerAmount = BigDecimalUtils.sub(takerAmount, tradeAmount, scale);
            		 takerCount = BigDecimalUtils.sub(takerCount, tradeCount, symbol.getCount_scale());
            	 }

                 if(tradeCount.compareTo(BigDecimal.ZERO)<=0 || tradeAmount.compareTo(BigDecimal.ZERO)<=0) {
                	 break;
                 }

                 TradeDetail tradeDetail = new TradeDetail();
                 tradeDetail.setAmount(tradeAmount);
                 tradeDetail.setCount(tradeCount);
                 tradeDetail.setPrice(tradePrice);
                 tradeDetail.setTimestamp(ts);
                 tradeDetail.setCd(makerOrder.getSymbol());
                 tradeDetail.setTaker_order_id(order.getId());
                 tradeDetail.setMaker_order_id(makerOrder.getId());
                 tradeDetail.setSequence(matchSeq++);
                 if(matchSeq>MAX_SEQ) { matchSeq=1; }

                 if (matchLongSeq > MAX_LONG_SEQ) {
                     matchLongSeq = 1l;
                 }


                 //takerEntrust.setLeftCount(Utils.sub(takerEntrust.getLeftCount(), tradeCount, scale));
                 //takerEntrust.setLeftAmount(Utils.mul(takerEntrust.getLeftCount(), takerEntrust.getPrice(), scale));

                 makerOrder.setCount(BigDecimalUtils.sub(makerOrder.getCount(), tradeCount, symbol.getCount_scale()));
                 makerOrder.setAmount(BigDecimalUtils.sub(makerOrder.getAmount(), tradeAmount, scale));


                 //成交了
                 if(tradeDetail.getCount().compareTo(BigDecimal.ZERO)>0) {
                     matchList.add(tradeDetail);
                 }else{
                     log.error("id=[{}]-step5_msg_failed, entrustMatchDto validated failed: takerCount={},price={}, count={}, amount={}", order.getId(),takerCount, tradePrice, tradeCount, tradeAmount);
                 }

                 //处理剩余内容
                 if(makerOrder.getCount().compareTo(BigDecimal.ZERO)>0) { //maker部分成交
                     makerService.addEntrustAsMaker(makerOrder);
                 }else{
                     makerService.removeMaker(makerOrder); //maker全部成交
                 }
             }else {
            	 break;
             }
         }

         if(takerCount.compareTo(BigDecimal.ZERO)>=0) {
             if (takerCount.compareTo(BigDecimal.ZERO)>0) {
                 order.setCount(takerCount);
                 order.setAmount(takerAmount);
                 makerService.addEntrustAsMaker(order);
             }
         }

        return matchList;
    }


}
