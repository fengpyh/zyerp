package com.cryptomind.ymatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import com.cryptomind.dto.CancelDetail;
import com.cryptomind.dto.TradeDetail;
import com.google.gson.Gson;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Component
public class OrderConsumerMsgHandler implements MessageListener {

	

	@Resource
	private TakerService takerService;

    @Resource
    private MakerService makerService;
    


    private Gson gson = new Gson();

    public final static String ORDER_IN_CHANNEL = "ORDER-IN";

    /**
     * 启动 subscriber
     */
    @Override
    public void onMessage(Message message, byte[] pattern1) {
    	
        long start = System.currentTimeMillis();
        String msgStr = new String(message.getBody());
        String channel = new String(message.getChannel());
        String pattern = new String(pattern1);
        //String tn = Thread.currentThread().getId() + "-" + Thread.currentThread().getName();
        String tag = channel.substring(ORDER_IN_CHANNEL.length()+1);
        //log.info(" GOT_MSG-{}, channel={}, tag={}, pattern={}, msg.length={}", c, channel,  tag ,pattern, msgStr.length());
        //log.info("GOT_MSG, tag={}, msg.length={}, subMsg:{}", tag, msgStr.length(), msgStr.length()>50?msgStr.substring(0,50):msgStr);
        log.info("GOT_MSG, tag={}, msg.length={}, subMsg:{}", tag, msgStr.length(), msgStr);

        
        OrderDto entrust = gson.fromJson(msgStr, OrderDto.class);
        
        //trading-deal-detail-0
        //log.info("GOT_MSG tag={}, {}", tag, tag.startsWith("trading-deal-detail"));
        if(tag.startsWith("A")) {
        	List<TradeDetail> dto = takerService.processOrder(entrust);
        }else if(tag.startsWith("B")) {
        	List<CancelDetail> dto = takerService.processCancel(entrust);
        }else {
        	log.warn("UNKNOWN MSG: {}, {}, {}", tag, msgStr.length(), msgStr);
        }
        long end = System.currentTimeMillis();
        log.info("GOT_MSG msg-{}, {}, {}, {} mills",  channel, pattern,tag, end-start);
    }
}
