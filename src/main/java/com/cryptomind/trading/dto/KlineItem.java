package com.cryptomind.trading.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KlineItem implements Serializable {
    private static final long serialVersionUID = 7234177524838067749L;
    private String o;
    private String h;
    private String l;
    private String v;
    private String c;
    private long ot;
    private long ct;
    private long t;
}