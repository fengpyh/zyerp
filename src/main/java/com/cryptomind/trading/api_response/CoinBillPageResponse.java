package com.cryptomind.trading.api_response;

import lombok.Data;

import java.io.Serializable;

@Data
public class CoinBillPageResponse implements Serializable {

    //当前页
    private Integer currentPage;

    //每页大小
    private Integer sizePage;

    //总页数
    private Integer totalPage;

    //总条数
    private Integer totalCount;

}