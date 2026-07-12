package com.cryptomind.ymatch;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;


import java.util.*;

/**
 * 为定位深度冻结问题，临时增加的日志类
 * 目的在于维护一份简短的深度信息，定时打印
 */
@Data
@Slf4j
class MarketDepthLogCollection {

    /**
     * MakerServiceImpl：
     *    private Map<Integer, TreeMap<EntrustDto, EntrustDto>> makerBuyMap = new HashMap<>();//实际撮合使用的买单内存
     *    private Map<Integer, TreeMap<EntrustDto, EntrustDto>> makerSellMap = new HashMap<>();//实际撮合使用的卖单内存
     *
     *    private Map<Integer, MakerDepthCollection> makerPriceElementMap = new HashMap<>();//按交易对维护的行情深度信息
     *
     * MakerDepthCollection：
     *   private TreeMap<BigDecimal, Map<Long, EntrustDto>> buyPriceMap;//买单:价格->委托单集合
     *   private TreeMap<BigDecimal, Map<Long, EntrustDto>> sellPriceMap;//卖单:价格->委托单集合
     *   private TreeMap<BigDecimal, DepthElementDTO> depthBuyTreeMap; //买单:价格->深度对象
     *   private TreeMap<BigDecimal, DepthElementDTO> depthSellTreeMap;//卖单:价格->深度对象
     *
     */

    //展示深度只取价格第一档


    private String cd;

    private DepthDTO_Element depthSell;

    private DepthDTO_Element depthBuy;

    private List<OrderDto> depthBuyList;

    private List<OrderDto> depthSellList;

    //推送深度
    private List<DepthDTO_Element> buyDepth;

    private List<DepthDTO_Element> sellDepth;

    // 实际撮合内存 取最前面五个订单

    private List<OrderDto> makerBuyList;

    private List<OrderDto> makerSellList;


    public MarketDepthLogCollection(String cd, MakerDepthCollection makerDepthCollection,
                         TreeMap<OrderDto, OrderDto> buyEntrusts,
                         TreeMap<OrderDto, OrderDto> sellEntrusts) {

        if (null == cd || null == makerDepthCollection) {
            return;
        }

        this.cd = cd;

        if (!CollectionUtils.isEmpty(makerDepthCollection.getDepthSellTreeMap())) {
            depthSell = makerDepthCollection.getDepthSellTreeMap().firstEntry().getValue();
        }

        if (!CollectionUtils.isEmpty(makerDepthCollection.getBuyPriceMap())) {

            depthBuy = makerDepthCollection.getDepthBuyTreeMap().firstEntry().getValue();
        }

        depthBuyList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(makerDepthCollection.getBuyPriceMap())) {
            Map<Long, OrderDto> buyMap = makerDepthCollection.getBuyPriceMap().firstEntry().getValue();
            if (!CollectionUtils.isEmpty(buyMap)) {
                firstFiveElement(buyMap.values().iterator(),depthBuyList);
            }
        }


        depthSellList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(makerDepthCollection.getSellPriceMap())) {
            Map<Long, OrderDto> sellMap = makerDepthCollection.getSellPriceMap().firstEntry().getValue();

            if (!CollectionUtils.isEmpty(sellMap)) {
                firstFiveElement(sellMap.values().iterator(),depthSellList);
            }
        }

        sellDepth = new ArrayList<>();
        buyDepth = new ArrayList<>();

        if (null != makerDepthCollection.getDepth()) {
            List<DepthDTO_Element> buyList = makerDepthCollection.getDepth().getBuyDepth();
            List<DepthDTO_Element> sellList = makerDepthCollection.getDepth().getSellDepth();

            if (!CollectionUtils.isEmpty(buyList)) {
                if (buyList.size() > 5) {
                    buyDepth = buyList.subList(0,5);
                } else {
                    buyDepth = buyList;
                }
            }

            if (!CollectionUtils.isEmpty(sellList)) {
                if (sellList.size() > 5) {
                    sellDepth = sellList.subList(0,5);
                } else {
                    sellDepth = sellList;
                }
            }
        }

        makerBuyList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(buyEntrusts)) {
            firstFiveElement(buyEntrusts.values().iterator(),makerBuyList);
        }

        makerSellList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(sellEntrusts)) {
            firstFiveElement(sellEntrusts.values().iterator(),makerSellList);
        }

//        buyPriceMap = buyMap.values().stream().sorted(Comparator.comparingInt(o -> o.getId().intValue()))
//                .limit(5).collect(Collectors.toMap(EntrustDto::getId,v->v));
    }

    private void firstFiveElement(Iterator<OrderDto> iterator,List<OrderDto> list) {
        int cnt = 0;
        while (iterator.hasNext() && cnt < 5) {
            OrderDto entrustDto = iterator.next();
            list.add(entrustDto);
            cnt++;
        }
    }

    public String getLog() {

        if (null == cd) {
            return null;
        }

        if (null != depthBuy && null != depthSell) {
            if (depthBuy.getPrice().compareTo(depthSell.getPrice()) >= 0) {
                return exceptionLog();
            }
        }
        return simpleLog();
    }

    private String simpleLog() {

        StringBuilder sb = new StringBuilder(" SimpleLog--> TradeMappingId:").append(cd);

        sb.append(" { BuyPrice:").append(null != depthBuy ? depthBuy.getPrice() : "")
                .append(" SellPrice:").append(null != depthSell ? depthSell.getPrice() : "")
                .append(" RealBuyPrice:").append(!CollectionUtils.isEmpty(makerBuyList) ? makerBuyList.get(0).getPrice() : "")
                .append(" RealSellPrice:").append(!CollectionUtils.isEmpty(makerSellList) ? makerSellList.get(0).getPrice() : "").append(" }");
        return sb.toString();
    }

    private String exceptionLog() {
        StringBuilder sb = new StringBuilder(" ExceptionLog--> TradeMappingId:").append(cd);

        sb.append(" { BuyPrice:").append(depthBuy.getPrice()).append(" SellPrice:").append(depthSell.getPrice())
                .append(" BuyCnt:").append(depthBuy.getCoinCount()).append(" SellCnt:").append(depthSell.getCoinCount())
                .append(" BuyOrders:").append(depthBuy.getElementCount()).append(" SellOrders:").append(depthSell.getElementCount()).append(" }")
                .append(" { DepthBuyList:").append(depthBuyList).append(" DepthSellList:").append(depthSellList).append(" }")
                .append(" { DepthPushList:").append(depthBuy).append(" DepthPushList:").append(depthSell).append(" }")
                .append(" RealBuyList:").append(makerBuyList).append(" RealSellList:").append(makerSellList);

        return sb.toString();
    }
}
