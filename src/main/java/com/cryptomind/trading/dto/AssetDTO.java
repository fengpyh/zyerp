package com.cryptomind.trading.dto;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cryptomind.trading.utils.MoneyUtils;

public  interface AssetDTO {

    @Builder
    @Data
    @ToString
    public static class Asset implements Serializable {
        private BigDecimal balance;
        // 估值： 法币
        private String fiatValuation;
        // 估值： BTC
        private String btcValuation;


        public void add(BigDecimal balance) {
            if (balance != null) {
                this.balance = this.balance.add(balance);
            }
        }

        public void cal(BigDecimal localRate, BigDecimal btcRate) {
            fiatValuation = balance.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
            if (localRate.compareTo(BigDecimal.ZERO) != 0 && btcRate.compareTo(BigDecimal.ZERO) != 0) {
                btcValuation = MoneyUtils.divide(
                        MoneyUtils.divide(balance, localRate, MoneyUtils.AMOUNT_PRECISION),
                        btcRate,
                        MoneyUtils.AMOUNT_PRECISION).toPlainString();
            } else {
                btcValuation = MoneyUtils.scaleByPrecision(BigDecimal.ZERO, MoneyUtils.AMOUNT_PRECISION).toPlainString();
            }

        }

    }


    @Data
    @ToString
    public static class AssetV2 implements Serializable {
        private BigDecimal balanceBTC = BigDecimal.ZERO;
        private BigDecimal balanceLocal = BigDecimal.ZERO;

        public void add(String balanceBTC, String balanceLocal) {
            this.balanceBTC = this.balanceBTC.add(new BigDecimal(balanceBTC));
            this.balanceLocal = this.balanceLocal.add(new BigDecimal(balanceLocal));
        }

        public String getFiatValuation(){
            return MoneyUtils.discardByPrecision(this.balanceLocal, MoneyUtils.FIAT_AMOUNT_PRECISION).toPlainString();
        }

        public String getBtcValuation(){
            return MoneyUtils.discardByPrecision(this.balanceBTC, MoneyUtils.AMOUNT_PRECISION).toPlainString();
        }

    }




}

