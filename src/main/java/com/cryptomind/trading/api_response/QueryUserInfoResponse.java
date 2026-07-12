package com.cryptomind.trading.api_response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class QueryUserInfoResponse implements Serializable {
    private UserAccountInfoResponse accountInfo;
    private UserKycInfoResponse kycInfo;
    /**
     * 此参数无用，暂做保留
     */
    private UserRoleInfoResponse roleInfo;
    private UserLevelResponse levelInfo;
    private UserLogInfoResponse logInfo;
    private Integer introUserTotal;
}
