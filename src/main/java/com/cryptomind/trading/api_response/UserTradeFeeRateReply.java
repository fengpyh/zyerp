package com.cryptomind.trading.api_response;

import lombok.Data;

@Data
public class UserTradeFeeRateReply {
    private Long userId;
    private Long symbolId;
    private String takerRate;
    private String makerRate;
    private String decuction;// 0-不抵扣 1-抵扣
    private String deductionDiscount;//抵扣折扣
    private String discountMakerRate;//折后maker费率
    private String discountTakerRate;//折后taker费率
    private Long expireTime;//过期时间 单位：毫秒 将来的某个时间戳超过这个时间戳过期UTC时间
}
