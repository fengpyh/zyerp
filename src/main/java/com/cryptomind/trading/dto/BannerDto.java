package com.cryptomind.trading.dto;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class BannerDto {
    private String bannerImageUrl;
    private String remark;
    private String url;

}
