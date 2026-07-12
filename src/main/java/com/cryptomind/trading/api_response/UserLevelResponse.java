package com.cryptomind.trading.api_response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@ToString
@Builder
public class UserLevelResponse implements Serializable {
    private Integer level;
    private Integer internalLevel;   // 等级
    private String levelLimit;       // 等级限制
    private String  kycStatus;       // 主栈KYC状态
    private String  fiatKycStatus;   // 法币KYC状态
    private List<Map<String, Object>> levelDesc;
}
