package com.cryptomind.trading.api_response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

import com.cryptomind.trading.dto.AssetCoinVO;

@Data
@ToString
@Builder
public class AllCoinResponse implements Serializable {
    private List<AssetCoinVO> assetCoinList;
}
