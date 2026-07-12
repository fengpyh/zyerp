package com.cryptomind.dto;

public class OrderConst {


	//buy
    public static final String BUY = "buy";
    //sell
    public static final String SELL = "sell";
    
    
    public static final String ORDER_TYP_LIMIT = "limit";
    public static final String ORDER_TYP_MARKET = "market";
    
    public static final String ORDER_PENDING = "pending";
    
    public static final String ORDER_SUBMITTED = "submitted";
    
    public static final String ORDER_CLOSED = "closed";
    
    //filled_status VARCHAR(20) DEFAULT 'not_filled' comment 'not_filled,partial_filled,full_filled',
    //cancel_status VARCHAR(20) DEFAULT 'not_cancel' comment 'not_cancel,partial_cancel,full_cancel',
    
    
    public static final String ORDER_OP_PLACE = "place";
    public static final String ORDER_OP_CANCEL = "cancel";
    
    public static final String ORDER_FS_NOT_FILLED = "not_filled";
    public static final String ORDER_FS_PARTIAL_FILLED = "partial_filled";
    public static final String ORDER_FS_FULL_FILLED = "full_filled";
    
    public static final String ORDER_FS_NOT_CANCEL = "not_cancel";
    public static final String ORDER_FS_PARTIAL_CANCEL = "partial_cancel";
    public static final String ORDER_FS_FULL_CANCEL = "full_cancel";
}
