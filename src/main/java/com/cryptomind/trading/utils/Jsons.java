package com.cryptomind.trading.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Created by chengli.wang on 2018/10/29.
 *
 * JSON 工具类
 */
@Slf4j
public class Jsons {

    private static final ObjectMapper BASIC = new BasicObjectMapper();

    private static class BasicObjectMapper extends ObjectMapper {
        private BasicObjectMapper() {
            super();
            configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
    }


    public static String toJson(Object obj) {
        if (Utils.isBlank(obj)) {
            return null;
        }

        try {
            return BASIC.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Object(%s) to json exception", obj), e);
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return BASIC.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(String.format("json(%s) to object(%s) exception", json, clazz.getName()), e);
        }
    }

    public static <T> T toObjectNil(String json, Class<T> clazz) {
        try {
            return BASIC.readValue(json, clazz);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error(String.format("json (%s) to object (%s)", json, clazz.getName()), e);
            }
        }

        return null;
    }

    public static <T> List<T> toList(String json, Class<T> clazz) {
        try {
            return BASIC.readValue(json, BASIC.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            throw new RuntimeException(String.format("json(%s) to List<%s> exception", json, clazz.getName()), e);
        }
    }

    public static <T> List<T> tiListNil(String json,Class<T> clazz) {
        try {
            return BASIC.readValue(json, BASIC.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error(String.format("json(%s) to List<%s> exception", json, clazz.getName()), e);
            }
            return null;
        }
    }
}
