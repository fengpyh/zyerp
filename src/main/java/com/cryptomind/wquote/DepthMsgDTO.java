package com.cryptomind.wquote;

import lombok.Data;

import java.io.Serializable;

@Data
public class DepthMsgDTO implements Serializable {
    private static final long serialVersionUID = -6764725225502342727L;
    private String symbol;
    private DepthDTO depth;
    private Long timestamp;

}
