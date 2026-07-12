package com.cryptomind.wquote;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
public class DepthElementDTO {

    private BigDecimal price;
    private BigDecimal coinCount;
    private Integer    elementCount;
}
