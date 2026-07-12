package com.cryptomind.trading.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class NoticeShowDto {
    private Integer classfy;
    private String name;
    private String url;
}
