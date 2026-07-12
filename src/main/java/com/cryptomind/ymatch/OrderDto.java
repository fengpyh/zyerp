package com.cryptomind.ymatch;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;



@NoArgsConstructor
@ToString
@Data
@AllArgsConstructor
@Builder
public class OrderDto implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String symbol;
    private String side;  //buy, sell
    private BigDecimal price;
    private BigDecimal amount;
    private BigDecimal count;
    private String order_type; //limit, market
    private String operation; //place, cancel

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public boolean equals(Object obj) {
        if(this==obj) {
            return true;
        }

        if(obj instanceof OrderDto) {
            OrderDto newObj = (OrderDto)obj;
            return newObj.getId().equals(this.getId());
        }

        return false;
    }

}
