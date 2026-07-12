package com.cryptomind.trading.api_response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class UserLogInfoResponse implements Serializable {
    private Long latestLoginTimestamp;
    private String latestLoginIp;
    private String latestLoginEquipment;
}
