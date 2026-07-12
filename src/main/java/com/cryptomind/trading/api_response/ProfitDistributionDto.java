package com.cryptomind.trading.api_response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfitDistributionDto {
    private String amount; //: "501460.000000000000000"
    private String coin; //: "MOONSHOT"
    private Integer coinId; //: 2243
    private Long settlementTime; //: 1629113110110
    private String settlementType; //: "staking"
    private Integer state;
}
