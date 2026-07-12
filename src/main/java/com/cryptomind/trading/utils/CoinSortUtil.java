package com.cryptomind.trading.utils;


import com.cryptomind.trading.dto.AssetCoinPairVO;
import com.cryptomind.trading.dto.AssetCoinVO;
import com.google.common.collect.ImmutableMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class CoinSortUtil {

    // 加个空格，是英文有些币种名称还带有特殊字符
    private static final Map<String, String> SORT_FIRST = ImmutableMap.<String, String>builder()
            .put("USD",  " 10")
            .put("USDT", " 11")
            .put("BMX",  " 12")
            .put("BTC",  " 13")
            .put("ETH",  " 14")
            .put("XRP",  " 15")
            .put("BCH",  " 16")
            .put("LTC",  " 17")
            .put("ZEC",  " 18")
            .put("EOS",  " 19")
            .build();

    private static final Function<AssetCoinVO, String> SORT_FUNCTION = p -> {
        String s = SORT_FIRST.get(p.getCoinShortName());
        return s == null ? p.getCoinShortName() : s;
    };

    /**
     * 默认将USD币种显示在第一个，其他几种主流币种排序如下：
     * USDT 、BMX、BTC、ETH、XRP 、BCH、LTC、ZEC、EOS
     * 其他币种默认按币种简称从a-z排序
     */
    public static List<AssetCoinVO> sort(List<AssetCoinVO> coinList){
        if (coinList.isEmpty()) {
            return new ArrayList<>();
        }

        return coinList.stream().sorted(Comparator.comparing(SORT_FUNCTION)).collect(Collectors.toList());
    }

    // pair sort

    private static final Map<String, String> PAIR_SORT_FIRST = ImmutableMap.<String, String>builder()
            .put("BTC/USDT",  "1")
            .put("ETH/USDT",  "2")
            .put("BMX/USDT",  "3")
            .build();

    private static final Function<AssetCoinPairVO, String> PAIR_SORT_FUNCTION = p -> {
        String s = PAIR_SORT_FIRST.get(p.getTag());
        return s == null ? p.getTag() : s;
    };

    public static List<AssetCoinPairVO> pairSort(List<AssetCoinPairVO> list) {
        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        return list.stream().sorted(Comparator.comparing(PAIR_SORT_FUNCTION)).collect(Collectors.toList());

    }

    public static void main(String[] args) {
        TestSort();
    }

    private static void TestPairSort() {
        List<AssetCoinPairVO> list = new ArrayList<>();
        list.add(new AssetCoinPairVO().setSymbol(1).setTag("BTC/USDT"));
        list.add(new AssetCoinPairVO().setSymbol(2).setTag("AV/USDT"));
        list.add(new AssetCoinPairVO().setSymbol(2).setTag("BMX/USDT"));
        list.add(new AssetCoinPairVO().setSymbol(2).setTag("ETH/USDT"));
        list.add(new AssetCoinPairVO().setSymbol(2).setTag("E12/USDT"));

        System.out.println(pairSort(list));
    }

    private static void TestSort() {
        List<AssetCoinVO> assetCoinVOList = new ArrayList<>(20);
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("USD").coinType(1).build());
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("$KING").coinType(1092).build());
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("BBK").coinType(3).build());
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("CNM").coinType(6).build());
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("BCH").coinType(1).build());
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("ABT").coinType(9).build());
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("BTC").coinType(1).build());
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("BMX").coinType(2).build());
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("ETH").coinType(14).build());
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("USDT").coinType(15).build());
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("EOS").coinType(19).build());
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("LTC").coinType(12).build());
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("ZUTE").coinType(11).build());
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("ZEC").coinType(13).build());
        assetCoinVOList.add(AssetCoinVO.builder().coinShortName("XRP").coinType(16).build());
        // CoinSortUtil.sort(assetCoinVOList).listIterator().forEachRemaining(System.out::println);

        assetCoinVOList = sort(assetCoinVOList);
        assetCoinVOList.listIterator().forEachRemaining(System.out::println);
    }
}
