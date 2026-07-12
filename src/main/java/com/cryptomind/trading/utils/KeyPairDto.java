package com.cryptomind.trading.utils;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class KeyPairDto {
    private String privateKey;
    private String publicKey;

    private String aesPrivateKey;
}
