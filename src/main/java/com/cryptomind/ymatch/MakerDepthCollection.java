package com.cryptomind.ymatch;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @Auther:ryu
 * @Date:2020-01-09 19:06
 * @Description
 */
@Data
@Slf4j
class MakerDepthCollection {
    private static PriceDepthComparator DESC = new PriceDepthComparator(MakerPriceComparator.SORT_DESC);
    private static PriceDepthComparator ASC = new PriceDepthComparator(MakerPriceComparator.SORT_ASC);
    private static int MAX_DEPTH_SIZE = 500;
    private TreeMap<BigDecimal, Map<Long, OrderDto>> buyPriceMap;//买单:价格->委托单集合
    private TreeMap<BigDecimal, Map<Long, OrderDto>> sellPriceMap;//卖单:价格->委托单集合
    private TreeMap<BigDecimal, DepthDTO_Element> depthBuyTreeMap; //买单:价格->深度对象
    private TreeMap<BigDecimal, DepthDTO_Element> depthSellTreeMap;//卖单:价格->深度对象

    private DepthDTO depthDTO;//深度

    public MakerDepthCollection() {
        buyPriceMap = new TreeMap<>(DESC);
        sellPriceMap = new TreeMap<>(ASC);
        depthBuyTreeMap = new TreeMap<>(DESC);
        depthSellTreeMap = new TreeMap<>(ASC);
        depthDTO = new DepthDTO();
    }

    public OrderDto removeMaker(OrderDto order) {
        OrderDto param = new OrderDto();
        BeanUtils.copyProperties(order,param);
        if (MatchEnum.BUY.equals( order.getSide() )) {
            return removeEntrust(buyPriceMap, param, depthBuyTreeMap, depthDTO, MatchEnum.BUY);
        }
        if (MatchEnum.SELL.equals( order.getSide() )) {
            return removeEntrust(sellPriceMap, param, depthSellTreeMap, depthDTO, MatchEnum.SELL);
        }
        return null;
    }

    public void addAsMaker(OrderDto order) {
        OrderDto param = new OrderDto();
        BeanUtils.copyProperties(order,param);
        if (MatchEnum.BUY.equals( order.getSide() )) {
            addEntrust(buyPriceMap, param, depthBuyTreeMap, depthDTO);
        }
        if (MatchEnum.SELL.equals( order.getSide() )) {
            addEntrust(sellPriceMap, param, depthSellTreeMap, depthDTO);
        }
    }

    public DepthDTO getDepth() {
        return depthDTO;
    }

    private OrderDto removeEntrust(Map<BigDecimal, Map<Long, OrderDto>> priceMap, OrderDto entrustDtoAfterMatch, TreeMap<BigDecimal, DepthDTO_Element> depthElementDTOTreeMap, DepthDTO depth, String side) {
        long start = System.currentTimeMillis();
        //log.info("ryu:remove entrust:{}", JsonUtil.object2json(entrustDtoAfterMatch));
        Map<Long, OrderDto> map = priceMap.get(entrustDtoAfterMatch.getPrice());
        boolean mapIsUpdated = false;//未进行操作
        OrderDto entrustBeforeMatch = null;
        if (map != null && map.size() > 0) {
            entrustBeforeMatch = map.remove(entrustDtoAfterMatch.getId());
            //log.info("ryu:remove entrustID:{}", entrustDtoAfterMatch.getId());
            mapIsUpdated = true;
            if (map.isEmpty()) {
                map = null;
                //log.info("ryu:remove price:{}", entrustDtoAfterMatch.getPrice());
                priceMap.remove(entrustDtoAfterMatch.getPrice());
            }
        }
        long removeTime = System.currentTimeMillis();
        //重新计算这个价格的深度
        if (mapIsUpdated) {
            //log.info("ryu:重新构造深度 opt=delete");
            int opt = 0;
            constructThePriceDepth(entrustDtoAfterMatch.getPrice(), map, depthElementDTOTreeMap, depth, entrustDtoAfterMatch, entrustBeforeMatch, DepthConstructEnum.removeEntrust);
        }
        //log.info("ryu:removeEntrust span times:{}ms,removetime:{}ms,depthtime:{}", System.currentTimeMillis() - start, removeTime - start, System.currentTimeMillis() - removeTime);
        return entrustBeforeMatch;
    }

    private void addEntrust(Map<BigDecimal, Map<Long, OrderDto>> priceMap, OrderDto entrustDtoAfterMatch, TreeMap<BigDecimal, DepthDTO_Element> depthElementDTOTreeMap, DepthDTO depth) {
        //log.info("ryu:addEntrust-entrustDtoAfterMatch:{}", JsonUtil.object2json(entrustDtoAfterMatch));
        Map<Long, OrderDto> map = priceMap.get(entrustDtoAfterMatch.getPrice());
        if (map == null) {
            map = new HashMap<>();
            priceMap.put(entrustDtoAfterMatch.getPrice(), map);
        }
        OrderDto entrustBeforeMatch = map.get(entrustDtoAfterMatch.getId());
        //log.info("ryu:addEntrust-entrustBeforeMatch:{}", JsonUtil.object2json(entrustBeforeMatch));
        map.put(entrustDtoAfterMatch.getId(), entrustDtoAfterMatch);
        //log.info("ryu:addEntrust-put-aftermatch-entrustBeforeMatch:{}", JsonUtil.object2json(entrustBeforeMatch));
        //重新计算这个价格的深度
        constructThePriceDepth(entrustDtoAfterMatch.getPrice(), map, depthElementDTOTreeMap, depth, entrustDtoAfterMatch, entrustBeforeMatch, DepthConstructEnum.addEntrust);
    }

    //重新计算这个价格的深度
    private void constructThePriceDepth(BigDecimal price, Map<Long, OrderDto> entrustsMap, TreeMap<BigDecimal, DepthDTO_Element> depthElementDTOTreeMap, DepthDTO depth, OrderDto entrustAfterMatch, OrderDto entrustBeforeMatch, int opt) {
        long start = System.currentTimeMillis();
        if (entrustsMap == null || entrustsMap.isEmpty()) {
            depthElementDTOTreeMap.remove(price);
            //log.info("ryu:entrustsMap null,remove price:{}", price);
        } else {
            DepthDTO_Element elementDTO = depthElementDTOTreeMap.get(entrustAfterMatch.getPrice());
            //log.info("ryu:elementDTO:{}", JsonUtil.object2json(elementDTO));
            if (elementDTO == null) {
                elementDTO = new DepthDTO_Element();
            }
            if (DepthConstructEnum.removeEntrust == opt) {
                elementDTO.removeEntrust(entrustAfterMatch, entrustBeforeMatch);
                elementDTO.resetElementCount(entrustsMap.size());
                //log.info("ryu:removeEntrust-elementDTO:{}", JsonUtil.object2json(elementDTO));
            } else {
                if (entrustBeforeMatch != null) {
                    //log.info("ryu:entrustBeforeMatch:{},entrustDtoAfterMatch:{}", JsonUtil.object2json(entrustBeforeMatch), JsonUtil.object2json(entrustAfterMatch));
                    elementDTO.reAddEntrust(entrustAfterMatch, entrustBeforeMatch);
                    elementDTO.resetElementCount(entrustsMap.size());
                    //log.info("ryu:reAddEntrust-elementDTO:{}", JsonUtil.object2json(elementDTO));
                } else {
                    elementDTO.addEntrust(entrustAfterMatch);
                    elementDTO.resetElementCount(entrustsMap.size());
                    //log.info("ryu:addEntrust-elementDTO:{}", JsonUtil.object2json(elementDTO));
                }
            }
            if (elementDTO.getCoinCount() != null && elementDTO.getCoinCount().compareTo(BigDecimal.ZERO) > 0) {
                depthElementDTOTreeMap.put(price, elementDTO);
                //log.info("ryu:entrustsMap not null,compute depth price:{},elementDTO:{}", price, JsonUtil.object2json(elementDTO));
            }
        }
        long middle = System.currentTimeMillis();
        if (depth == null) {
            depth = new DepthDTO();
        }
        if (MatchEnum.BUY.equals(entrustAfterMatch.getSide() )) {
            depth.setBuyDepth(depthElementDTOTreeMap.values().stream().collect(Collectors.toList()));
        }
        if (MatchEnum.SELL.equals(entrustAfterMatch.getSide()  )) {
            depth.setSellDepth(depthElementDTOTreeMap.values().stream().collect(Collectors.toList()));
        }
        //log.info("ryu:depth={}", JsonUtil.object2json(depth));
        depthDTO = depth;
        long end = System.currentTimeMillis();
        //log.info("ryu:constructThePriceDepth-construct price depth:{}ms,todepth:{}ms,total:{}ms", middle - start, end - middle, end - start);
    }

    public boolean clear() {
        buyPriceMap.clear();
        sellPriceMap.clear();
        depthBuyTreeMap.clear();
        depthSellTreeMap.clear();
        if (depthDTO != null) {
            depthDTO.getBuyDepth().clear();
            depthDTO.getSellDepth().clear();
        }
        return true;
    }
}
