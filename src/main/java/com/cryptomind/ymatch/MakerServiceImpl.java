package com.cryptomind.ymatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


import java.util.*;


@Service
@Slf4j
@Scope("singleton")
public class MakerServiceImpl implements MakerService {


    //<tradeMapping, Set<Entrust>>
    private Map<String, TreeMap<OrderDto, OrderDto>> makerBuyMap = new HashMap<>();
    private Map<String, TreeMap<OrderDto, OrderDto>> makerSellMap = new HashMap<>();

    private Map<String, MakerDepthCollection> makerPriceElementMap = new HashMap<>();

    private MakerPriceComparator desc = new MakerPriceComparator(MakerPriceComparator.SORT_DESC);
    private MakerPriceComparator asc = new MakerPriceComparator(MakerPriceComparator.SORT_ASC);

    private TreeMap<OrderDto, OrderDto> getMakerOrders(String cd, String side) {
        TreeMap<OrderDto, OrderDto> orders = null;
        if(side.equals(MatchEnum.BUY) ) {
            orders = makerBuyMap.get(cd);
        }else if(side.equals( MatchEnum.SELL) ) {
            orders = makerSellMap.get(cd);
        }
        return orders;
    }


    @Override
    public  OrderDto getMaker(String cd, String side) {
        TreeMap<OrderDto, OrderDto> entrusts = getMakerOrders(cd, side);

        if(entrusts!=null && entrusts.size()>0) {
            OrderDto entrust = entrusts.firstKey();
            return entrust;
        }
        return null;
    }

    @Override
    public  OrderDto removeMaker(OrderDto order) {
        String cd = order.getSymbol();
        String side = order.getSide();
        TreeMap<OrderDto, OrderDto> orders = getMakerOrders(cd, side);
        MakerDepthCollection collection = makerPriceElementMap.get(cd);


        if(orders!=null && orders.size()>0) {
            OrderDto deleteOrder = orders.remove(order);
            OrderDto orderDto = collection.removeMaker(order);
            return deleteOrder;
        }else{
            return null;
        }
    }

    @Override
    public  void addEntrustAsMaker(OrderDto order) {
        if(order.getSide().equals(MatchEnum.BUY) ) {
            addMakerMap(makerBuyMap, desc, order);
        }else if(order.getSide().equals(MatchEnum.SELL) ) {
            addMakerMap(makerSellMap, asc, order);
        }else{

            log.error("new_trading->unknow entrust_type: {}", order);
        }
    }

    private void addMakerMap(Map<String, TreeMap<OrderDto, OrderDto>> makerMap, Comparator<OrderDto> priceCompartor,OrderDto order) {
        //单线程代码,不需要加锁
        TreeMap<OrderDto, OrderDto> treeMap = makerMap.get(order.getSymbol());
        if (treeMap == null) {
            treeMap = new TreeMap<>(priceCompartor);
            makerMap.put(order.getSymbol(), treeMap);
        }

        treeMap.put(order, order);

        MakerDepthCollection collection = makerPriceElementMap.get(order.getSymbol());
        if (collection == null) {
            collection = new MakerDepthCollection();
        }
        collection.addAsMaker(order);
        makerPriceElementMap.put(order.getSymbol(),collection);
    }

    @Override
    public void clear() {
        makerBuyMap = new HashMap<>();
        makerSellMap = new HashMap<>();
        makerPriceElementMap = new HashMap<>();
    }

    @Override
    public void clearById(String cd) {
        if (makerBuyMap.get(cd) != null) {
            makerBuyMap.get(cd).clear();
        }
        if (makerSellMap.get(cd) != null) {
            makerSellMap.get(cd).clear();
        }
        if (makerPriceElementMap.get(cd) != null) {
            MakerDepthCollection collection = makerPriceElementMap.get(cd);
            collection.clear();
        }
    }

//    private static PriceDepthComparator DESC = new PriceDepthComparator(MakerPriceComparator.SORT_DESC);
//    private static PriceDepthComparator ASC = new PriceDepthComparator(MakerPriceComparator.SORT_ASC);
//    private static int MAX_DEPTH_SIZE = 50;

    public DepthDTO getDepth(String cd) {
        MakerDepthCollection collection = makerPriceElementMap.get(cd);
        if (collection == null) {
            return new DepthDTO();
        }
        DepthDTO depthDTO = collection.getDepth();
        if (depthDTO == null) {
            return new DepthDTO();
        }
        return depthDTO;
    }

    @Override
    public MakerDepthCollection getDepthCollection(String cd) {
        return makerPriceElementMap.get(cd);
    }

    @Override
    public TreeMap<OrderDto, OrderDto> getMakerEntrusts(String cd, String side) {
        return getMakerOrders(cd, side);
    }

    @Override
    public boolean isMakerPriceError(String cd) {
        //取推送行情的深度信息校验
        try {
            DepthDTO depthDTO = getDepth(cd);
            List<DepthDTO_Element> buyDepth = depthDTO.getBuyDepth();
            List<DepthDTO_Element> sellDepth = depthDTO.getSellDepth();

            if (CollectionUtils.isEmpty(buyDepth) || CollectionUtils.isEmpty(sellDepth)) {
                return false;
            } else {
                DepthDTO_Element buy = buyDepth.get(0);
                DepthDTO_Element sell = sellDepth.get(0);
                return buy.getPrice().compareTo(sell.getPrice()) >= 0;
            }
        } catch (Exception e) {
            log.error("CheckMakerPriceException:{}",e);
            return false;
        }
    }

//    private void constructDepth(TreeMap<EntrustDto, EntrustDto> treeMap, TreeMap<BigDecimal, DepthElementDTO> depthElementDTOTreeMap) {
//        if (treeMap != null) {
//            Iterator<EntrustDto> iterator = treeMap.keySet().iterator();
//            while (iterator.hasNext() && depthElementDTOTreeMap.size() < MAX_DEPTH_SIZE) {
//                EntrustDto entrust = iterator.next();
//                DepthElementDTO elementDTO = depthElementDTOTreeMap.get(entrust.getPrice());
//                if (elementDTO == null) {
//                    elementDTO = new DepthElementDTO();
//                    elementDTO.initEntrust(entrust);
//                    depthElementDTOTreeMap.put(elementDTO.getPrice(), elementDTO);
//                } else {
//                    elementDTO.addEntrust(entrust);
//                }
//            }
//        }
//    }
}
