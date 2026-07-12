package com.cryptomind.ymatch;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepthMsgDTO {

    private String symbol;
    private DepthDTO depth;
    private Long timestamp;

}
