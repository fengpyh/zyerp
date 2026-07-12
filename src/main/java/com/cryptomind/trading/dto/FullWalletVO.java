package com.cryptomind.trading.dto;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class FullWalletVO implements Serializable {
    private String totalBalance;
    private String availableBalance;
    // 交易冻结
    private String frozenBalance;
    private int coinType;
    private String coinShortName;

    private String usdRate;

}

