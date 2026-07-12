package com.cryptomind.ymatch;


import java.util.List;

import com.cryptomind.dto.CancelDetail;
import com.cryptomind.dto.TradeDetail;


public interface TakerService {

    List<TradeDetail> processOrder(OrderDto entrust);
    List<CancelDetail> processCancel(OrderDto entrust);

    void _reload();

}
