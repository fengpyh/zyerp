package com.cryptomind.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManualCancelAllDto {

    // 200 表示执行成功 500:内部系统出错
    private Integer status;

    //订单执行信息
    private String message;

    //撤销数量
    private Integer cancelNum;
}
