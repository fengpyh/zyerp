package com.cryptomind.trading.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author ryu
 * on 07/07/2018.
 */
@Slf4j
public class JsonUtil {
    private JsonUtil() {
        throw new IllegalStateException("Utility class for JsonUtil");
    }

    public static final ObjectMapper objectMapper;
    public static ObjectNode _DATA = null;

    static {
        objectMapper = new ObjectMapper();
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        _DATA = JsonUtil.objectMapper.createObjectNode();
    }

    public static <T> Optional<String> toJson(T obj) {
        try {
            return Optional.of(objectMapper.writeValueAsString(obj));
        } catch (Exception e) {
            LogUtil.error(log, LogUtil.LogType.EXCEPTION, "toJson", "{}", e);
            return Optional.empty();
        }
    }

    public static <T> String object2json(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            LogUtil.error(log, LogUtil.LogType.EXCEPTION, "toJson", "{}", e);
            return null;
        }
    }

    public static <T> Optional<T> fromJson(String jsonStr, Class<T> tClass) {
        try {
            return Optional.of(objectMapper.readValue(jsonStr, tClass));
        } catch (Exception e) {
            LogUtil.error(log, LogUtil.LogType.EXCEPTION, "fromJson", "{}", e);
            return Optional.empty();
        }
    }

    public static <E> Optional<List<E>> fromJson2List(String json, Class<E> tClass){
        try {
            return Optional.ofNullable(objectMapper.readValue(json,objectMapper.getTypeFactory().constructCollectionType(List.class,tClass)));
        }catch (Exception e){
            LogUtil.error(log, LogUtil.LogType.EXCEPTION, "fromJson2List", "{}", json, e);
            return Optional.empty();
        }
    }

    public static <K,V> Optional<Map<K,V>> fromJson2Map(String json, Class<K> kClass, Class<V> vClass){
        try {
            return Optional.ofNullable(objectMapper.readValue(json,objectMapper.getTypeFactory().constructMapType(Map.class,kClass,vClass)));
        }catch (Exception e){
            LogUtil.error(log, LogUtil.LogType.EXCEPTION, "fromJson2Map", "{}", json, e);
            return Optional.empty();
        }
    }


    public static Optional<Integer> getIntCode(String responseBody) {
        try {
            JsonNode node = objectMapper.readTree(responseBody);
            JsonNode code = node.get("code");
            if (code != null) {
                return Optional.of(code.asInt());
            }
        } catch (Exception e) {
            log.error("get response code.error", e);
        }

        return Optional.empty();
    }
}
