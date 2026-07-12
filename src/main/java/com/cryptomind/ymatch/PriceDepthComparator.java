package com.cryptomind.ymatch;

import java.math.BigDecimal;
import java.util.Comparator;

class PriceDepthComparator implements Comparator<BigDecimal> {

    public final static int SORT_DESC = 1;
    public final static int SORT_ASC = 2;

    private int sortOrder;

    public PriceDepthComparator(int sortOrder) {
        this.sortOrder = sortOrder;
    }


    @Override
    public int compare(BigDecimal o1, BigDecimal o2) {
        int ret = o1.compareTo(o2);
        if (sortOrder == SORT_ASC) {
            return ret;
        } else {
            return 0 - ret;
        }
    }
}
