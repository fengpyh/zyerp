package com.cryptomind.trading.api_response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Builder
public class TradeMappingResult implements Serializable {

    //交易对数据列表
    private List<TradeMappingDataVO> result;
    //语言
    private String local;

}
