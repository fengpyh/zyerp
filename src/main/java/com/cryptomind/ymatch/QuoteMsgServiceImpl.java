package com.cryptomind.ymatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cryptomind.dto.TradeDetail;
import com.cryptomind.entity.CmSymbol;
import com.cryptomind.service.SymbolService;
import com.cryptomind.trading.utils.ExceptionUtil;
import com.cryptomind.trading.utils.JsonUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class QuoteMsgServiceImpl {

    @Value("${topic.depth}")
    private String depthTopic;

    @Value("${topic.deal}")
    private String dealTopic;
    @Autowired
    private SymbolService symbolService;

    @Autowired
    private MakerService makerService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void publish(String topic, String tag, String message){
        try {
            //Message msg = new Message(topic, tag, tag, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
            //producer.send(msg);
            String channel =String.format("%s.%s", topic, tag);
            stringRedisTemplate.convertAndSend(channel, message);
            //if(tag.equals("trade-depth-match-0") && message.length()> DEPTH_LEN+10) {
            //    log.info("redis.pub, {}, {}", channel, message);
            //}

            //if(tag.equals("trading-deal-detail-0") && message.length()> DEAL_LEN+10) {
            //    log.info("redis.pub, {}, {}", channel, message);
            //}

        }catch (Exception e) {
            log.error("publishFastklineMakerMsg, {}", ExceptionUtil.toString(e));
        }
    }

    private void sendDepth(String symbol, DepthDTO depth) {
        DepthMsgDTO message = new DepthMsgDTO(symbol, depth, System.currentTimeMillis());
        //depthMessageService.publish(depthTopicFullName, tradeMappingId.toString(), JsonUtil.object2json(message), SendMode.ASYNC);
        publish("quotation-store", depthTopic, JsonUtil.object2json(message));
    }

    private void sendDealDetails(String symbol, List<TradeDetail> dealDetails) {
        if(dealDetails!=null && !dealDetails.isEmpty()) {
            String msg = JsonUtil.object2json(dealDetails);
            publish("quotation-store", dealTopic, msg);
            log.info("sendDealDetails.SEND: {},{}, {}", dealTopic,symbol, msg);
        }else{
            log.warn("sendDealDetails.EMPTY: {}, {}", symbol, dealDetails);
        }
    }

    @Scheduled(cron = "* 0/1 * * * ? ")
    public void tradeMsgSendTask() {
            try {
                List<CmSymbol> list = symbolService.findSymbolList();
                List<TradeDetail> emptyList = new ArrayList<>(0);
                for (CmSymbol r : list) {
                    sendDealDetails(r.getSymbol(), emptyList);
                }
                Thread.sleep(1000*60L);
            } catch (Exception e) {
                log.error("DealDetailMonitorThread - error:{}", ExceptionUtil.toString(e));
            }
    }

    @Scheduled(cron = "* 0/1 * * * ? ")
    public void orderBookSendTask() {

        while (true) {
            try {
                long count = 0l;
                List<CmSymbol> list = symbolService.findSymbolList();
                for (CmSymbol r : list) {
                    orderBookMsgSend(r);
                }
                Thread.sleep(1000*60);
            } catch (Exception e) {
                log.error("DepthMonitorThread - error:{}", e);
            }
        }
    }

    public void tradeMsgSend(CmSymbol symbol, List<TradeDetail> tradeList) {
        if (symbol == null) {
            return;
        }
        if (! symbol.getStatus().equals("active")) {
            return;
        }
        sendDealDetails(symbol.getCd(), tradeList);
    }



    public void orderBookMsgSend(CmSymbol symbol) {
        if (symbol == null) {
            return;
        }
        if (! symbol.getStatus().equals("active")) {
            return;
        }
        DepthDTO depth = makerService.getDepth(symbol.getCd());

        //author:tree
        //解释买一送一深度不一致的问题，猜测可能是remove失败导致的买一卖一不一致，暂时只能出现这种问题就重新计算深度
        List<DepthDTO_Element> buyDepth = depth.getBuyDepth();
        List<DepthDTO_Element> sellDepth = depth.getSellDepth();
        if (!CollectionUtils.isEmpty(buyDepth) && !CollectionUtils.isEmpty(sellDepth)) {
            DepthDTO_Element buy = buyDepth.get(0);
            DepthDTO_Element sell = sellDepth.get(0);
            if (buy.getPrice().compareTo(sell.getPrice()) >= 0){
                log.error("##### ORDERBOOK.Attention: buyPrice >= sellPrice,  {}:{}, {}", symbol.getCd(), buy.getPrice(), sell.getPrice());
                log.error("##### ORDERBOOK.Attention: buyPrice >= sellPrice,  {}:{}, {}", symbol.getCd(), buy.getPrice(), sell.getPrice());
                log.error("##### ORDERBOOK.Attention: buyPrice >= sellPrice,  {}:{}, {}", symbol.getCd(), buy.getPrice(), sell.getPrice());
                log.error("##### ORDERBOOK.Attention: buyPrice >= sellPrice,  {}:{}, {}", symbol.getCd(), buy.getPrice(), sell.getPrice());
                log.error("##### ORDERBOOK.Attention: buyPrice >= sellPrice,  {}:{}, {}", symbol.getCd(), buy.getPrice(), sell.getPrice());
                log.error("##### ORDERBOOK.Attention: buyPrice >= sellPrice,  {}:{}, {}", symbol.getCd(), buy.getPrice(), sell.getPrice());
                log.error("##### ORDERBOOK.Attention: buyPrice >= sellPrice,  {}:{}, {}", symbol.getCd(), buy.getPrice(), sell.getPrice());
                log.error("##### ORDERBOOK.Attention: buyPrice >= sellPrice,  {}:{}, {}", symbol.getCd(), buy.getPrice(), sell.getPrice());
                //takerService.reload();
                return;
            }
        }

        sendDepth(symbol.getCd(), depth);
    }
}
