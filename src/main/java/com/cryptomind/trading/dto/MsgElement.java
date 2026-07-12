package com.cryptomind.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Auther:ryukp
 * @Date:2019-12-16 19:53
 * @Description
 */
@Data
@AllArgsConstructor
public class MsgElement {
    private Integer tradeMappingId;
    private String data;
    private Long timestamp;
}
