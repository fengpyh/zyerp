package com.cryptomind.ymatch;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
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
class MakerCollection {
    private static PriceDepthComparator DESC = new PriceDepthComparator(MakerPriceComparator.SORT_DESC);
    private static PriceDepthComparator ASC = new PriceDepthComparator(MakerPriceComparator.SORT_ASC);
    private static int MAX_DEPTH_SIZE = 500;
    private TreeMap<BigDecimal, Map<Long, OrderDto>> buyPriceMap;//买单:价格->委托单集合
    private TreeMap<BigDecimal, Map<Long, OrderDto>> sellPriceMap;//卖单:价格->委托单集合
    private TreeMap<BigDecimal, DepthDTO_Element> depthBuyTreeMap; //买单:价格->深度对象
    private TreeMap<BigDecimal, DepthDTO_Element> depthSellTreeMap;//卖单:价格->深度对象

    private DepthDTO depthDTO;//深度

    public MakerCollection() {
        buyPriceMap = new TreeMap<>(DESC);
        sellPriceMap = new TreeMap<>(ASC);
        depthBuyTreeMap = new TreeMap<>(DESC);
        depthSellTreeMap = new TreeMap<>(ASC);
        depthDTO = new DepthDTO();
    }

    public OrderDto removeMaker(OrderDto order) {
        if (MatchEnum.BUY.equals( order.getSide() )) {
            return removeEntrust(buyPriceMap, order, depthBuyTreeMap, depthDTO, MatchEnum.BUY);
        }
        if (MatchEnum.SELL.equals( order.getSide() )) {
            return removeEntrust(sellPriceMap, order, depthSellTreeMap, depthDTO, MatchEnum.SELL);
        }
        return null;
    }

    public void addAsMaker(OrderDto order) {
        if (MatchEnum.BUY.equals(order.getSide()) ) {
            addEntrust(buyPriceMap, order, depthBuyTreeMap, depthDTO);
        }
        if (MatchEnum.SELL.equals(order.getSide())  ) {
            addEntrust(sellPriceMap, order, depthSellTreeMap, depthDTO);
        }
    }

    public DepthDTO getDepth() {
        return depthDTO;
    }

    private OrderDto removeEntrust(Map<BigDecimal, Map<Long, OrderDto>> priceMap, OrderDto order, TreeMap<BigDecimal, DepthDTO_Element> depthElementDTOTreeMap, DepthDTO depth, String side) {
        long start = System.currentTimeMillis();
//        log.info("ryu:remove entrust:{}", JsonUtil.object2json(entrustDto));
        Map<Long, OrderDto> map = priceMap.get(order.getPrice());
        boolean mapIsUpdated = false;//未进行操作
        OrderDto deleteEntrust = null;
        if (map != null && map.size() > 0) {
            deleteEntrust = map.remove(order.getId());
//            log.info("ryu:remove entrustID:{}", entrustDto.getId());
            mapIsUpdated = true;
            if (map.isEmpty()) {
                map = null;
//                log.info("ryu:remove price:{}", entrustDto.getPrice());
                priceMap.remove(order.getPrice());
            }
        }
        long removeTime = System.currentTimeMillis();
        //重新计算这个价格的深度
        if (mapIsUpdated) {
//            log.info("ryu:重新构造深度");
            constructThePriceDepth(order.getPrice(), map, depthElementDTOTreeMap, depth, side);
        }
//        log.info("ryu:removeEntrust span times:{}ms,removetime:{}ms,depthtime:{}", System.currentTimeMillis() - start, removeTime - start, System.currentTimeMillis() - removeTime);
        return deleteEntrust;
    }

    private void addEntrust(Map<BigDecimal, Map<Long, OrderDto>> priceMap, OrderDto order, TreeMap<BigDecimal, DepthDTO_Element> depthElementDTOTreeMap, DepthDTO depth) {
        Map<Long, OrderDto> map = priceMap.get(order.getPrice());
        if (map == null) {
            map = new HashMap<>();
            priceMap.put(order.getPrice(), map);
        }
        map.put(order.getId(), order);
        //重新计算这个价格的深度
        constructThePriceDepth(order.getPrice(), map, depthElementDTOTreeMap, depth,order.getSide());
    }

    //重新计算这个价格的深度
    private void constructThePriceDepth(BigDecimal price, Map<Long, OrderDto> entrustsMap, TreeMap<BigDecimal, DepthDTO_Element> depthElementDTOTreeMap, DepthDTO depth, String side) {
        //long start = System.currentTimeMillis();
        if (entrustsMap == null || entrustsMap.isEmpty()) {
            depthElementDTOTreeMap.remove(price);
//            log.info("ryu:entrustsMap null,remove price:{}", price);
        } else {
            DepthDTO_Element elementDTO = null;
            Iterator<OrderDto> iterator = entrustsMap.values().iterator();
            while (iterator.hasNext()) {
                OrderDto entrust = iterator.next();
                if (elementDTO == null) {
                    elementDTO = new DepthDTO_Element();
                    elementDTO.initEntrust(entrust);
                } else {
                    elementDTO.addEntrust(entrust);
                }
            }
            depthElementDTOTreeMap.put(price, elementDTO);
//            log.info("ryu:entrustsMap not null,compute depth price:{}", price);
        }
        long middle = System.currentTimeMillis();
        if (depth == null) {
            depth = new DepthDTO();
        }
        if (MatchEnum.BUY.equals(side)) {
            depth.setBuyDepth(depthElementDTOTreeMap.values().stream().collect(Collectors.toList()));
        }
        if (MatchEnum.SELL.equals(side)) {
            depth.setSellDepth(depthElementDTOTreeMap.values().stream().collect(Collectors.toList()));
        }
        //log.info("ryu:depth={}", JsonUtil.object2json(depth));
        depthDTO = depth;
        //long end = System.currentTimeMillis();
//        log.info("ryu:constructThePriceDepth-construct price depth:{}ms,todepth:{}ms,total:{}ms", middle - start, end - middle, end - start);
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
