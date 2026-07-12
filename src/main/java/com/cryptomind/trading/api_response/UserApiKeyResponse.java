package com.cryptomind.trading.api_response;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class UserApiKeyResponse  implements Serializable {
    private String apiId;
    private String apiMemo;
    private String apiKey;
    private String apiSecret;
    private int status;
    private int expireDay;
    private long expireTime;
    private long createTime;
    private String trustIpList;
    //private List<String> trustIpList;
    //private List<PermissionListResponse.PermissionVO> permissionList;

}
