package com.cryptomind.ymatch;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class DepthDTO_Element {

    private BigDecimal price;
    private BigDecimal coinCount;
    private Integer elementCount;

    public synchronized void initEntrust(OrderDto entrust) {
        this.price = entrust.getPrice();
        this.coinCount = entrust.getCount();
        this.elementCount = 1;
    }

    public synchronized void addEntrust(OrderDto entrust) {
        if (this.elementCount == null || this.elementCount <= 0) {
            this.price = entrust.getPrice();
            this.coinCount = entrust.getCount();
            this.elementCount = 1;
        } else {
            this.coinCount = this.coinCount.add(entrust.getCount());
            this.elementCount = this.elementCount + 1;
        }
    }

    public synchronized void reAddEntrust(OrderDto entrustAfterMatch, OrderDto entrustBeforeMatch) {
        if (elementCount == null || this.elementCount <= 0 || this.coinCount == null || this.coinCount.compareTo(BigDecimal.ZERO) <= 0) {
            this.price = entrustAfterMatch.getPrice();
            this.coinCount = entrustAfterMatch.getCount();
            this.elementCount = 1;
            return;
        }
        if (this.coinCount.compareTo(entrustAfterMatch.getCount()) < 0 || entrustBeforeMatch == null) {
            this.price = entrustAfterMatch.getPrice();
            this.coinCount = entrustAfterMatch.getCount();
            this.elementCount = 1;
            return;
        }
        if (entrustBeforeMatch.getPrice().compareTo(this.price) != 0) {
            return;
        }
        this.coinCount = this.coinCount.subtract(entrustBeforeMatch.getCount());
        this.coinCount = this.coinCount.add(entrustAfterMatch.getCount());
    }

    public synchronized void removeEntrust(OrderDto entrustAfterMatch, OrderDto entrustBeforeMatch) {
        if (elementCount == null || this.elementCount <= 0 || this.coinCount == null || this.coinCount.compareTo(BigDecimal.ZERO) <= 0) {
            return;
        }
        if (entrustBeforeMatch == null) {
            return;
        }
        if (this.coinCount.compareTo(entrustBeforeMatch.getCount()) < 0) {
            return;
        }
        if (entrustBeforeMatch.getPrice().compareTo(this.price) != 0) {
            return;
        }
        this.coinCount = this.coinCount.subtract(entrustBeforeMatch.getCount());
        this.elementCount = this.elementCount - 1;

    }

    public synchronized void resetElementCount(int size) {
        this.elementCount = size;
    }
}
