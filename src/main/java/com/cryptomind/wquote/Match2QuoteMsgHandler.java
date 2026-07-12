package com.cryptomind.wquote;

import com.cryptomind.entity.CmSymbol;
import com.cryptomind.service.SymbolService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 */
@Slf4j
@Component
public class Match2QuoteMsgHandler implements MessageListener {


    @Resource
    private KlineBiz klineBiz;

    @Resource
    private SymbolService symbolService;


    private final static String PREFIX = "quotation-store.";

    private Gson gson = new Gson();

    public Match2QuoteMsgHandler() {
    }

    /**
     * subscriber
     */
    @Override
    public void onMessage(Message message, byte[] pattern1) {

        long start = System.currentTimeMillis();
        String msgStr = new String(message.getBody());
        String channel = new String(message.getChannel());
        String pattern = new String(pattern1);
        String tag = channel.substring(PREFIX.length());
        log.info("GOT_MSG, tag={}, msg.length={}, subMsg:{}", tag, msgStr.length(), msgStr);

        if(tag.startsWith("trading-deal-detail")) {
            DealDetailMsgDTO detailMsgDTO = gson.fromJson(msgStr, DealDetailMsgDTO.class);

            //MEM
            CmSymbol symbolData = symbolService.findBySymbol(detailMsgDTO.getSymbol());
            log.info("symbolData: {}", symbolData);
            QuoteData quoteData = QuoteInstance.getInstance().dfsFind(symbolData.getSymbol());
            quoteData.handleDeal(detailMsgDTO);
            //log.info("GOT_MSG-{}, QuoteInstance.handleDeal tag={}, msg.length={}, detailMsgDTO:{}", tn, tag, msgStr.length(), detailMsgDTO);
            //DealDetailDTO deal = quoteData.getCurrentLatestDeal();
            H24PriceDTO priceDTO = quoteData.getH24PriceDTO();
            PriceMsg msg = new PriceMsg();
            msg.setUuid(UUID.randomUUID().toString());
            msg.setSubscribe("price");
            msg.setSymbol(symbolData.getSymbol());
            msg.setLocal("zh_CN");
            msg.setCode(0);
            Map<String, Object> map = new HashMap<>();
            map.put("o", priceDTO.getO());
            map.put("h", priceDTO.getH());
            map.put("l", priceDTO.getL());
            map.put("v", priceDTO.getVc());
            map.put("c", priceDTO.getC());

            map.put("increase", priceDTO.getCp());
            map.put("fluctuation", priceDTO.getCa());
            map.put("sign", "USD");
            map.put("rate", "0");
            map.put("time", priceDTO.getTime());
            msg.setData(map);

            //pushMsgHandler.pushMsg(symbolData.getTradeMappingId(), quoteData.getH24PriceDTO());
            //notifyService.synchronizedNotifyString("ws-manager", "price", gson.toJson(msg));
            //if(symbolData.getQuoteCoin().endsWith("USDT")) {
            //    msg.setSymbol(symbolData.getBaseCoin());
            //    notifyService.synchronizedNotifyString("ws-manager", "price", gson.toJson(msg));
            //}

            TradeMsg msgTrade = new TradeMsg();
            msgTrade.setUuid(UUID.randomUUID().toString());
            msgTrade.setCode(0);
            msgTrade.setSubscribe("trade");
            msgTrade.setSymbol(symbolData.getSymbol());
            msgTrade.setPrecision(symbolData.getPrice_scale());
            msgTrade.setFirstSubscribe("1");

            List<TradeMsg_TradeItem> itemList = new ArrayList<>();
            for(DealDetailDTO d: detailMsgDTO.getDealList()) {
                TradeMsg_TradeItem item = new TradeMsg_TradeItem();
                item.setTime(d.getDealTime());
                item.setPrice(d.getDealPrice().toPlainString());
                item.setAmount(d.getDealAmount().toPlainString());
                item.setCount(d.getDealCount().toPlainString());
                item.setIsBuy(d.getSide());
                item.setSequence(d.getSequence());
                itemList.add(item);
            }
            msgTrade.setTrades(itemList);
            msg.setData(map);
            //notifyService.synchronizedNotifyString("ws-manager", "trade", gson.toJson(msgTrade));
            //if(symbolData.getQuoteCoin().endsWith("USDT")) {
            //    msgTrade.setSymbol(symbolData.getBaseCoin());
            //    notifyService.synchronizedNotifyString("ws-manager", "trade", gson.toJson(msgTrade));
            //}
            //KLINE pushMsgHandler.pushMsg();
        }else if(tag.startsWith("trade-depth-match")) {
            //log.info("GOT_MSG, tag={}, msg.length={}, subMsg:{}", tag, msgStr.length(), msgStr);
            DepthMsgDTO msg = gson.fromJson(msgStr, DepthMsgDTO.class);
            //depthBiz.processDepth(msg);

            CmSymbol symbolData = symbolService.findBySymbol(msg.getSymbol());
            QuoteInstance.getInstance().dfsFind(symbolData.getSymbol()).setDepthMsgDTO(msg);

            //SEND MSG TO DEPTH
            DepthMsg depthMsg = new DepthMsg();
            depthMsg.setSubscribe("depth");
            depthMsg.setUuid(UUID.randomUUID().toString());
            depthMsg.setPrecision(symbolData.getPrice_scale());
            depthMsg.setCode(0);
            depthMsg.setPre_version("");
            depthMsg.setVersion("");
            depthMsg.setSymbol(msg.getSymbol());

            DepthMsg_Data data = new DepthMsg_Data();
            List<DepthMsg_DataItem> buys = new ArrayList<>();
            List<DepthMsg_DataItem> sells = new ArrayList<>();

            if(msg.getDepth()!=null && msg.getDepth().getBuyDepth()!=null) {
                for (DepthElementDTO item : msg.getDepth().getBuyDepth()) {
                    DepthMsg_DataItem i = new DepthMsg_DataItem();
                    i.setAmount(item.getElementCount().toString());
                    i.setPrice(item.getPrice().toPlainString());
                    i.setCount(item.getCoinCount().toPlainString());
                    i.setTotal(item.getCoinCount().toPlainString());
                    buys.add(i);
                }
            }
            if(msg.getDepth()!=null && msg.getDepth().getSellDepth()!=null) {

                for (DepthElementDTO item : msg.getDepth().getSellDepth()) {
                    DepthMsg_DataItem i = new DepthMsg_DataItem();
                    i.setAmount(item.getElementCount().toString());
                    i.setPrice(item.getPrice().toPlainString());
                    i.setCount(item.getCoinCount().toPlainString());
                    i.setTotal(item.getCoinCount().toPlainString());
                    sells.add(i);
                }
            }
            data.setBuys(buys);
            data.setSells(sells);
            depthMsg.setData(data);
            //notifyService.synchronizedNotifyString("ws-manager", "depth", gson.toJson(depthMsg));
        }else if(tag.startsWith("kline-saveUpdate-fix")) {
            klineBiz.klineSaveUpdateFix(msgStr);
        }else if(tag.startsWith("coin-meta")){
            //shszTickerBiz.processCmcMetaMsg(msgStr);
            log.warn("UnImplementation: coin-meta: {}", msgStr);
        }else{
            log.warn("GOT_MSG UNKNOWN CHANNEL: channel={}, tag={}, pattern={}, msg={}", channel,  tag ,pattern, msgStr);
        }
        long end = System.currentTimeMillis();
        log.info("GOT_MSG msg-{}, {}, {}, {} mills",  channel, pattern,tag, end-start);
    }
}
