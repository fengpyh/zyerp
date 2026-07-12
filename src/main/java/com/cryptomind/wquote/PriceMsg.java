package com.cryptomind.wquote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceMsg   {
    private String uuid;
    private String subscribe;//数据类型
    private int code;//返回码

    private String symbol;
    private String local;
    private String version;//版本
    private String pre_version;//先前的版本
    private Map<String, Object> data;
}
