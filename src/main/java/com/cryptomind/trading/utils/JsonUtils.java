package com.cryptomind.trading.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

/**
 * @Auther:ryukp
 * @Date:2019-08-19 16:57
 * @Description
 */
@Slf4j
public class JsonUtils {
    public static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T jsonString2Object(String message, Class<T> classOfT) throws IOException {
        return objectMapper.readValue(message, classOfT);
    }

    public static <T> Optional<T> toObject(String message, Class<T> classOfT) {
        try {
            T value = objectMapper.readValue(message, classOfT);
            if (value != null) {
                return Optional.of(value);
            }
            return Optional.empty();
        } catch (Exception e) {
            log.error("ex:{}", ExceptionUtils.getErrorInfoFromException(e));
            return Optional.empty();
        }
    }

    public static String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    private static Gson gson = new Gson();

    public static String gsonToJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T  gsonJsonToObject(String json,Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }
    
    public static <T> Optional<T> fromJson(String jsonStr, Class<T> tClass) {
        try {
            return Optional.of(objectMapper.readValue(jsonStr, tClass));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static final String writeValueAsString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("writeValueAsString exception=[{}]", ExceptionUtil.toString(e));
            return "";
        }
    }
}
