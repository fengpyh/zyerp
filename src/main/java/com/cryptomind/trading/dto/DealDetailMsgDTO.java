package com.cryptomind.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DealDetailMsgDTO {

    private String symbol;

    private Long time;

    private List<DealDetailDTO> dealList;
}
