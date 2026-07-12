package com.cryptomind.trading.api_request;

import lombok.Data;

@Data
public class QryUserSpotTradeFeeRateHttpRequest {
    public String uuid;
    public Long symbolId;
}
