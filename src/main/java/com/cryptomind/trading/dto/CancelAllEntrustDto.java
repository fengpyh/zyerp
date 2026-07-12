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
public class CancelAllEntrustDto {

	//这些字段,兼容api,后面去掉
    private Integer operation;
    private Long entrustId;
    private Integer status;
    private String message;
    
    //cancel不调用wallet
    private String walletCode;
    private String walletMessage;
    

    //新字段,最终字段
    private Integer totalCount;
    private Integer successCount;
    private Integer failedCount;
    private String msg;
}
