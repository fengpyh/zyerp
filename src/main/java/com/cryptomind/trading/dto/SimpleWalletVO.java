package com.cryptomind.trading.dto;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SimpleWalletVO implements Serializable {
    private String totalBalance;
    private String availableBalance;
    private String frozenBalance;
    private String lockBalance;
    private String coinSymbol;
    private int coinType;
    private String coinName;
    private String coinShortName;
    private String iconUrl;
}
