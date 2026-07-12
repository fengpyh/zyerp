package com.cryptomind.trading.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther:ryukp
 * @Date:2020-04-01 11:45
 * @Description
 */
@Data
public class UserCancelAllDTO implements Serializable {
    private String userId;
    private List<String> failOrders;
}
