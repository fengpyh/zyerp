package com.cryptomind.trading.api_response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TradeMappingDataVO implements Serializable {
    private String coinName;
    private String coinType;
    private String iconUrl;
    private List<TradeMappingDataResult> mappingList;
}
