package com.cryptomind.trading.api_response;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Accessors(chain = true)
public class WalletResponse implements Serializable {
    private List<Wallet> wallet;

    @Data
    @ToString
    @Accessors(chain = true)
    public static class Wallet implements Serializable{
        private String id;
        private String name;
        private String available;
        private String frozen;
    }
}
