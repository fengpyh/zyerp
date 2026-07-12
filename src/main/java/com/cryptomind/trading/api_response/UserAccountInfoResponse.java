package com.cryptomind.trading.api_response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class UserAccountInfoResponse implements Serializable {
    private Long userId;
    private String loginName;
    private String bindGoogle;
    private String bindPhone;
    private String bindEmail;
    private String userStatusEnabled;
    private String tradeStatusEnabled;
    private String depositFiatEnabled;
    private String withdrawFiatEnabled;
    private String depositVirtualEnabled;
    private String withdrawVirtualEnabled;
    private String contractTransferEnabled;
    private String otcTransferEnabled;
    private String otcTradeEnabled;
    private String phone;
    private String areaCode;
    private String email;
    private String userIntroNo;
    private Long registerTime;
}
