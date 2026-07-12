package com.cryptomind.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther:ryukp
 * @Date:2020-05-11 16:59
 * @Description
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFeeRateDTO implements Serializable {

    private static final long serialVersionUID = 4915006879542828050L;

    //private Long userId;
    //private Integer level;//用户等级
    //private BigDecimal takerRate;
    //private BigDecimal makerRate;
    private Long expireTime; //过期时间 未来某个时间的时间戳 UTC时间


    private Long userId;
    private String userRateType;//用户费率类型 0-普通用户 1-专业用户
    private String level;//用户费率等级
    //    private String tradeRangeID;//所属交易区ID
    private String tradeAmount;//最近30天成交量
    private String tradeAmountUnit;//成交量单位
    private String walletTotal;//钱包持仓量
    private String walletUnit;//钱包持仓单位
    private String takerRate;
    private String makerRate;
    private String deductionDiscount;//抵扣折扣
    private String discountMakerRate;//折后maker费率
    private String discountTakerRate;//折后taker费率
    private String deduction;

}
