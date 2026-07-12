package com.cryptomind.ymatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cryptomind.dto.*;
import com.cryptomind.entity.CmSymbol;
import com.cryptomind.service.SymbolService;
import com.cryptomind.trading.utils.ExceptionUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TradeMsgServiceImpl {


    @Autowired
    private SymbolService symbolService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void publishDeal(String message){
        try {
            //Message msg = new Message(topic, tag, tag, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
            //producer.send(msg);
            String channel =String.format("%s", "deal-notify");
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

    public void publishCancel(String message){
        try {
            //Message msg = new Message(topic, tag, tag, message.getBytes(RemotingHelper.DEFAULT_CHARSET));
            //producer.send(msg);
            String channel =String.format("%s", "cancel-notify");
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

}
