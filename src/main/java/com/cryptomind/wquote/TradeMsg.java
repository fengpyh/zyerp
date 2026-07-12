package com.cryptomind.wquote;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeMsg  {
    private String uuid;
    private String subscribe;//数据类型
    private int code;//返回码

    private String symbol;
    private Integer precision;
    private String firstSubscribe;//0-第一次 1第二次
    private List<TradeMsg_TradeItem> trades;
}
