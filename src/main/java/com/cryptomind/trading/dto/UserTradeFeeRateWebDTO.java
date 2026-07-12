package com.cryptomind.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTradeFeeRateWebDTO implements Serializable {
    private static final long serialVersionUID = -7463834493754830718L;
    private Long userId;
    private Long symbolId;
    private String userRateType;//用户费率类型 0-普通用户 1-专业用户
    private String level;//用户费率等级
    //    private String tradeAmount;//最近30天成交量
//    private String tradeAmountUnit;//成交量单位
//    private String walletTotal;//钱包BMX持仓量
    private String takerRate;
    private String makerRate;
    private String decuction;// 0-不抵扣 1-抵扣
    private String deductionDiscount;//抵扣折扣
    private String discountMakerRate;//折后maker费率
    private String discountTakerRate;//折后taker费率
    private Long expireTime;//过期时间 单位：毫秒 将来的某个时间戳超过这个时间戳过期UTC时间
    private String type;
}
