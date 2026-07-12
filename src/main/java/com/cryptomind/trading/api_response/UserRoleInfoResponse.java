package com.cryptomind.trading.api_response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Builder
public class UserRoleInfoResponse implements Serializable {
    private List<String> roleList;
}
