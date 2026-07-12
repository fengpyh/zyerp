package com.cryptomind.wquote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepthMsg_Data {
    List<DepthMsg_DataItem> buys;
    List<DepthMsg_DataItem> sells;
}