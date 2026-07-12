package com.cryptomind.wquote;

import lombok.Data;

import java.util.List;

@Data
public class DealDetailMsgDTO {

    private String symbol;

    private Long time;

    private List<DealDetailDTO> dealList;
}
