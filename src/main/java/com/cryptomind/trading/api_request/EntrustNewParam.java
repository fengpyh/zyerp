package com.cryptomind.trading.api_request;

import lombok.Data;

/**
 * Created by chengli.wang on 2018/12/5.
 */
@Data
public class EntrustNewParam {
    //主键
    private Long fid;
    //用户id
    private Integer fusFid;

    private Integer symbol;

    private Long startTime;

    private Long endTime;

    private String statuss;

    private Integer currentPage;

    private Integer pageSize;

    private String sourceChnl;

    private Integer entrustType;

    private Integer orderType;

}
