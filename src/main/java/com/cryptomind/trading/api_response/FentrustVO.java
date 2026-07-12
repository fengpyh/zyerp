package com.cryptomind.trading.api_response;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * <p></p>
 *
 * @author ryu
 * @version 2018/1/18
 */
public class FentrustVO {

    private Long id;
    private int orderType; // 订单类型
    private String symbolName;
    private long createTime;
    private int entrustType;
    private String price;
    private String successPrice;
    private String amount;
    private String fees;
    private String leftfees;
    private String successAmount;
    private String count;
    private String successCount;
    private String leftCount;
    private int status;
    private boolean isLimit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getEntrustType() {
        return entrustType;
    }

    public void setEntrustType(int entrustType) {
        this.entrustType = entrustType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getLeftfees() {
        return leftfees;
    }

    public void setLeftfees(String leftfees) {
        this.leftfees = leftfees;
    }

    public String getSuccessAmount() {
        return successAmount;
    }

    public void setSuccessAmount(String successAmount) {
        this.successAmount = successAmount;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(String successCount) {
        this.successCount = successCount;
    }

    public String getLeftCount() {
        return leftCount;
    }

    public void setLeftCount(String leftCount) {
        this.leftCount = leftCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isLimit() {
        return isLimit;
    }

    public void setLimit(boolean limit) {
        isLimit = limit;
    }

    public String getSuccessPrice() {
        return successPrice;
    }

    public void setSuccessPrice(String successPrice) {
        this.successPrice = successPrice;
    }

    public String toString(){
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
