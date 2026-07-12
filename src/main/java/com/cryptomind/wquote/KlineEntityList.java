package com.cryptomind.wquote;


import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class KlineEntityList {

    private String symbol;
    private String step; //1m,15m,1h,1d,1M,1D,1Y
    private Long ot;
    private Long ct;
    private KlineDTO[] data;
}
