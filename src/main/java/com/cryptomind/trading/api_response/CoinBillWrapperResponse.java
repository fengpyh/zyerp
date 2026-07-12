package com.cryptomind.trading.api_response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CoinBillWrapperResponse implements Serializable {

    private List<CoinBillResponse> bills;

    private CoinBillPageResponse page;

}
