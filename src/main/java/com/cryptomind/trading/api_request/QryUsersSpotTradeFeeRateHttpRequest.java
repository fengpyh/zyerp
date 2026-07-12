package com.cryptomind.trading.api_request;

import lombok.Data;

@Data
public class QryUsersSpotTradeFeeRateHttpRequest {
    public String uuid;
    public Long[] userId;
    public Long symbolId;
}
