package com.cryptomind.trading.dto;


import java.io.Serializable;

/**
 */
public class Hour24PriceEntity implements Serializable {
    private Integer ftradeMappingId;
    private Integer step;
    private Double openPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double closePrice;
    private Double volume;


    public Double getOpenPrice() {
        return getAttributesDouble(this.openPrice);
    }

    public Double getHighPrice() {
        return getAttributesDouble(this.highPrice);
    }

    public Double getLowPrice() {
        return getAttributesDouble(this.lowPrice);
    }

    public Double getClosePrice() {
        return getAttributesDouble(this.closePrice);
    }

    public Double getVolume() {
        return getAttributesDouble(this.volume);
    }


    private Double getAttributesDouble(Double f) {
        if (f == null) {
            return 0D;
        } else {
            return f;
        }
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public void setClosePrice(Double closePrice) {
        this.closePrice = closePrice;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Integer getFtradeMappingId() {
        if (ftradeMappingId == null) {
            return 0;
        }
        return ftradeMappingId;
    }


    public void setFtradeMappingId(Integer ftradeMappingId) {
        this.ftradeMappingId = ftradeMappingId;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    @Override
    public String toString() {
        return "Hour24PriceEntity{" +
                "ftradeMappingId=" + ftradeMappingId +
                ", step=" + step +
                ", openPrice=" + openPrice +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", closePrice=" + closePrice +
                ", volume=" + volume +
                '}';
    }
}
