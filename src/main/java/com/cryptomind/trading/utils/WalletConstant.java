package com.cryptomind.trading.utils;

public class WalletConstant {
    public static final String UNFROZEN_WALLET_SUFFIX = ":walletIdUnfrozen";
    public static final String WALLET_ID_KEY = ":walletKeyTrading";
    public static final String WALLET_ID_VALUE = ":walletId";

    public static String getKey(Long id) {
        return id + WALLET_ID_KEY;
    }

    public static String getValue(Long id) {
        return id + WALLET_ID_VALUE;
    }
    public static String getValue(String id) {
        return id + WALLET_ID_VALUE;
    }
    public static String getValue(Long taker, Long maker) {
        return taker + "-" + maker + WALLET_ID_VALUE;
    }
    public static Long getExpire() {
        return 1 * 24 * 60 * 60 * 1000L;//1day
    }
    public static String getUnFrozenWalletId(String id) {
        return id + UNFROZEN_WALLET_SUFFIX;
    }
    public static String getUnFrozenWalletId(Long id) {
        return id + UNFROZEN_WALLET_SUFFIX;
    }

}
