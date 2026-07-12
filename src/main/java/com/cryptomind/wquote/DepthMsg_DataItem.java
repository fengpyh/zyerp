package com.cryptomind.wquote;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepthMsg_DataItem {
    private String amount;
    private String total;
    private String price;
    private String count;
}