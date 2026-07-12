package com.cryptomind.trading.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * {
 * "id": 1,
 * "tradeMappingId": 22,
 * "userId": 1,
 * "createTime": 1231231231231, //毫秒
 * "lastUpdateTime": 123123123123, //毫秒
 * "entrustType": 0, //0=买, 1=卖
 * "price": 0.123,
 * "amount": 1.23,
 * "count": 10,
 * ”leftCount":10,
 * "status": 1, //1=going, 2=parital deal, 3=all deal, 4=cancel, 5=cancelling(这个还没有,要加一下)
 * "isLimit": true,
 * "operation": 1, //1=
 * "uuid": "1610ad5f-63e8-4d32-bc17-d2af0d5fe6e0",
 * }
 *
 * @author ryu
 * on 25/06/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EntrustDto implements java.io.Serializable {

    private static final long serialVersionUID = -4288111468185014753L;

    //主键 fid
    private Long id;

    private Integer tradeMappingId;

    private Integer userId;

    private Timestamp creaTime;

    private Timestamp lastUpdateTime;

    private Integer entrustType;

    private BigDecimal price;

    private BigDecimal amount;

    private BigDecimal count;

    private BigDecimal leftCount;

    private Integer status;

    private Boolean isLimit;

    private Integer operation;

    private String uuid;


    private String order_type;
    private String side;  //buy, sell


    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public boolean equals(Object obj) {
        if(this==obj) {
            return true;
        }

        if(obj instanceof EntrustDto) {
            EntrustDto newObj = (EntrustDto)obj;
            return newObj.getId().equals(this.getId());
        }

        return false;
    }
}
