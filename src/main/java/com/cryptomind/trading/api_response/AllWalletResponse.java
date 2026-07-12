package com.cryptomind.trading.api_response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

import com.cryptomind.trading.dto.FullWalletVO;

@Data
@ToString
@Builder
public class AllWalletResponse implements Serializable {
    private String local;
    private String currencySymbol;
    private String sumAssetsLocal;
    private String sumAssetsBtc;
    private int priceScale;
    private String frozenAssetsLocal;
    private String frozenAssetsBtc;
    private String availableAssetsLocal;
    private String availableAssetsBtc;
    private String lockAssetsLocal;
    private String lockAssetsBtc;
    private List<FullWalletVO> walletList;
}
