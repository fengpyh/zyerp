package com.cryptomind.trading.api_response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

import com.cryptomind.trading.dto.SimpleWalletVO;

@Data
@ToString
@Builder
public class CoinWalletResponse implements Serializable {
    private List<SimpleWalletVO> walletList;
}
