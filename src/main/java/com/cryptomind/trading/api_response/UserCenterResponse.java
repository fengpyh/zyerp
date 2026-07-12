package com.cryptomind.trading.api_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashMap;

import com.cryptomind.trading.dto.ApiCode;


@SuppressWarnings("unchecked")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCenterResponse<T> {
    @NonNull
    Integer code;
    @NonNull
    String msg;
    T data;
    // TODO 后续移除冗余的数据类型 (today: 2020/11/10)，主要是客户端老版本有用到
    T errorData;
    boolean success;

    @SuppressWarnings("rawtypes")
    public static UserCenterResponse ofSuccess() {
        return new UserCenterResponse(
                ApiCode.Common.SYSTEM_SUCCESS.getCode(),
                ApiCode.Common.SYSTEM_SUCCESS.getMsg(),
                new HashMap<>(),
                null,
                true
        );
    }

    public static <T> UserCenterResponse<T> ofSuccess(T response) {
        return new UserCenterResponse<>(
                ApiCode.Common.SYSTEM_SUCCESS.getCode(),
                ApiCode.Common.SYSTEM_SUCCESS.getMsg(),
                response,
                null,
                true
        );
    }

    public static <T> UserCenterResponse<T> ofSuccess(ApiCode.CodeMessage codeMessage, T response) {
        return new UserCenterResponse<>(
                codeMessage.getCode(),
                codeMessage.getMsg(),
                response,
                null,
                true
        );
    }

    public static UserCenterResponse ofFail(ApiCode.CodeMessage codeMessage) {
        return new UserCenterResponse(
                codeMessage.getCode(),
                codeMessage.getMsg(),
                new HashMap<>(),
                null,
                false
        );
    }

    public static UserCenterResponse ofFail(int code, String msg) {
        return new UserCenterResponse(code, msg, new HashMap<>(2), null, false);
    }

    public static <T> UserCenterResponse<T> ofFail(int code, String msg, T data) {
        return new UserCenterResponse(code, msg, data, data, false);
    }

    public static <T> UserCenterResponse<T> ofFail(ApiCode.CodeMessage codeMessage, T response) {
        return new UserCenterResponse<>(
                codeMessage.getCode(),
                codeMessage.getMsg(),
                response,
                response,
                false
        );
    }

    public static UserCenterResponse ofFail(ApiCode.CodeMessage codeMessage, String message) {
        return new UserCenterResponse<>(
                codeMessage.getCode(),
                message,
                new HashMap<>(),
                new HashMap<>(),
                false
        );
    }

    @SuppressWarnings("rawtypes")
    public static UserCenterResponse ofFail(String msg) {
        return new UserCenterResponse(
                ApiCode.Common.SYSTEM_FAILED.getCode(),
                msg,
                new HashMap<>(),
                null,
                false
        );
    }

    @SuppressWarnings("rawtypes")
    public static UserCenterResponse ofFail() {
        return new UserCenterResponse(
                ApiCode.Common.SYSTEM_FAILED.getCode(),
                ApiCode.Common.SYSTEM_FAILED.getMsg(),
                new HashMap<>(),
                null,
                false
        );
    }
}
