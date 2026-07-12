package com.cryptomind.trading.api_response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    @NonNull
    Integer code;

    @NonNull
    String msg;

    T data;

    T errorData;

    boolean success;




    public static<T> ApiResponse<T> ofSuccess(T response) {
        return new ApiResponse<>(
                0,
                "Success",
                response,
                null,
                true
        );
    }

    public static<T> ApiResponse<T> ofFail(T response) {
        return new ApiResponse<>(
                -1,
                "Fail",
                response,
                null,
                true
        );
    }

    public static<T> ApiResponse<T> of(Integer code, String codeMessage, T response) {
        return new ApiResponse<>(
                code,
                codeMessage,
                response,
                null,
                true
        );
    }


}
