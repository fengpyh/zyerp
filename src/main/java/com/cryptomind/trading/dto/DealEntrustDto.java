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
public class DealEntrustDto {

    //订单操作 1:下单 2:取消订单 3修改订单状态
    private Integer operation;

    //订单id
    private Long entrustId;

    // 200 表示执行成功 500:内部系统出错
    private Integer status;

    //钱包错误信息
    private String walletCode;

    //钱包返回信息
    private String walletMessage;

    //订单执行信息
    private String message;


}
