package com.cryptomind.trading.api_response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
@Data
public class EntrustLogRes implements java.io.Serializable{

    private Long id;

    private Long fenFid;

    private BigDecimal famount;

    private Timestamp fcreatetime;

    private BigDecimal fprize;

    private BigDecimal fcount;

    private Integer version;
    @JsonIgnore
    private boolean isactive;

    private Integer ftrademapping;

    private Integer fentrusttype;

    private BigDecimal ffees;

    private Long uid;

    private Long adversaryEnfid;

    private Integer accountmark;

    private Timestamp fupdatetime;

    private String walletid;

    //2018年12月19日新增字段，是否是用BMX抵扣，0：不使用，1：使用。历史数据为不使用
    private Boolean deductionStatus;

    //2018年12月19日新增字段，收取的手续费币种id
    private Integer deductionCoinId;

    //2018年12月19日新增字段，手续费用BMX抵扣，需要的BMX数量
    private  BigDecimal deductionCount;
    @JsonProperty("isactive")
    public boolean getIsactive() {
        return isactive;
    }
    @JsonProperty("active")
    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
}
