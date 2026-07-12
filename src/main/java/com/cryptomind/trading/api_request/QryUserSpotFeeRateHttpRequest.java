package com.cryptomind.trading.api_request;

import lombok.Data;

@Data
public class QryUserSpotFeeRateHttpRequest {
    public Long symbolId;
    public String uuid;
}
