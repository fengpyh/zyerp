package com.cryptomind.trading.api_request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class VirtualCoinBillRequest implements Serializable {

    @NotNull(message = "currentPage is null")
    private Integer currentPage;

    @NotNull(message = "sizePage is null")
    private Integer sizePage;

    @NotNull(message = "operationType is null")
    private Integer operationType;

    private Integer coinType;

}
