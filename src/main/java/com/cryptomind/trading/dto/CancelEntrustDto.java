package com.cryptomind.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ryu
 * on 03/09/2018.
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelEntrustDto {

	//这些字段,兼容api,后面去掉
    private Integer operation;
    private Long entrustId;
    private Integer status;
    private String message;

    
    //TODO cancel没有wallet消息
    private String walletCode;
    private String walletMessage;
}
