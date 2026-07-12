package com.cryptomind.wquote;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KlineDTO {

    private String          _id;
    private String          symbol;
    private KlineStep       step;
    private Long        ts;  //mills
    private String      h;
    private String      o;
    private String      l;
    private String      c;
    private String      v;
    private String      prev_c;
    private String      vol_ccy; //quote 计价
    private String      vol_ccy_quote;  //美元计价成交额(V-USD)

    private Long        ot;
    private Long        ct;
}
