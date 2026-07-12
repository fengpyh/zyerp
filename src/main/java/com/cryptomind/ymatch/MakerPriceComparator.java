package com.cryptomind.ymatch;

import java.util.Comparator;

class MakerPriceComparator implements Comparator<OrderDto> {

    public final static int SORT_DESC = 1;
    public final static int SORT_ASC = 2;

    private int sortOrder;

    public MakerPriceComparator(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(OrderDto o1, OrderDto o2) {

        int ret = o1.getPrice().compareTo(o2.getPrice());
        if(ret==0) {
            int a1 = o1.getId().intValue();
            int a2 = o2.getId().intValue();
            return a1 - a2;
        }else{
            if(sortOrder==SORT_ASC) {
                return ret;
            }else{

                return 0-ret;
            }
        }
    }
}
