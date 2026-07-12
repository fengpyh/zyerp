package com.cryptomind.trading.api_response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class UserKycInfoResponse implements Serializable {
    private Byte kycStatus;

    private String country;

    private String identityBackImageId;
    private String identityFrontImageId;
    private String identityHandheldImageId;

    private String realName;
    private Integer identityType;
    private String identityNo;
    private Byte sex;
    private String birthday;
}
