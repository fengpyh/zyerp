package com.cryptomind.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepthGraphItemVO {
	private String price = "0"; //价格
    private String count = "0";  //订单个数
    private String amount = "0"; //份数
    private String total = "0"; //订单个数
}
