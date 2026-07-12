package com.cryptomind.trading.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pair<K, V> {
    K left;
    V right;

    public static <K, V> Pair<K, V> of(K left, V right) {
        return new Pair<>(left, right);
    }
}
