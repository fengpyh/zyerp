package com.cryptomind.ymatch;


import java.util.TreeMap;


/**
 *
 * maker订单容器
 *
 */
public interface MakerService {


    /**
     *
     * @param id tradeMappingID
     * @param entrustType maker的entrustType
     * @return
     */
    OrderDto getMaker(String cd, String side);

    OrderDto removeMaker(OrderDto entrust);

    void addEntrustAsMaker(OrderDto entrust);

    void clear();
    void clearById(String cd);

    public DepthDTO getDepth(String cd);

    MakerDepthCollection getDepthCollection(String cd);

    TreeMap<OrderDto, OrderDto> getMakerEntrusts(String cd, String side);

    boolean isMakerPriceError(String cd);
}
