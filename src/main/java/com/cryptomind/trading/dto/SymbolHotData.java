package com.cryptomind.trading.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@ToString
@Builder
//@Accessors(chain = true)
public class SymbolHotData implements Serializable {
    private Long id;
    private Integer symbolId;
    private String symbolName;
    private Integer sortIndex;
}
