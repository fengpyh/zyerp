package com.cryptomind.trading.api_response;

import lombok.Data;

@Data
public class UserTradeFeeRateReplyWeb {
    private Long userId;
    private String userRateType;//用户费率类型 0-普通用户 1-专业用户
    private String level;//用户费率等级
    private Long symbolId;
    private String takerRate;
    private String makerRate;
    private String decuction;// 0-不抵扣 1-抵扣
    private String deductionDiscount;//抵扣折扣
    private String discountMakerRate;//折后maker费率
    private String discountTakerRate;//折后taker费率
    private Long expireTime;//过期时间 单位：毫秒 将来的某个时间戳超过这个时间戳过期UTC时间
    private String type;
}
