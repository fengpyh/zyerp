package com.cryptomind.ymatch;

import lombok.Data;

import java.util.List;

@Data
public class DepthDTO {

    private List<DepthDTO_Element> buyDepth;
    private List<DepthDTO_Element> sellDepth;

    public List<DepthDTO_Element> getBuyDepth() {
        if (buyDepth != null && buyDepth.size() > 500) {
            buyDepth = buyDepth.subList(0, 500);
        }
        return buyDepth;
    }

    public void setBuyDepth(List<DepthDTO_Element> buyDepth) {
        if (buyDepth != null && buyDepth.size() > 500) {
            buyDepth = buyDepth.subList(0, 500);
        }
        this.buyDepth = buyDepth;
    }

    public List<DepthDTO_Element> getSellDepth() {
        if (sellDepth != null && sellDepth.size() > 500) {
            sellDepth = sellDepth.subList(0, 500);
        }
        return sellDepth;
    }

    public void setSellDepth(List<DepthDTO_Element> sellDepth) {
        if (sellDepth != null && sellDepth.size() > 500) {
            sellDepth = sellDepth.subList(0, 500);
        }
        this.sellDepth = sellDepth;
    }
}
