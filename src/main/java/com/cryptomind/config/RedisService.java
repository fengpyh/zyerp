package com.cryptomind.config;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public interface RedisService {
    //----------------------save-------------------------------



    void publish(String topic, String message);

    /**
     * 保存或更新key-value 信息到redis
     *
     * @param key   key
     * @param value value
     */
    void save(String key, String value);

    /**
     * 带超时时间的保存key-value
     *
     * @param key      key
     * @param value    value
     * @param time     超时时间长度
     * @param timeUnit 超时时间单位
     */
    void saveWithTimeOut(String key, String value, Long time, TimeUnit timeUnit);

    /**
     * 保存key-value 若有重复无法保存
     *
     * @param key   key
     * @param value value
     * @return 是否保存成功
     */
    Boolean saveIfAbsent(String key, String value) ;

    /**
     * 批量保存,只能保存不存在的key-value
     *
     * @param map 批量要保存的数据
     * @return 是否保存成功
     */
    Boolean multiSaveIfAbsent(Map<String, String> map) ;

    /**
     *  批量更新保存key-value
     * @param map
     */
    void multiupsert(Map<String, String> map) ;

    //----------------------expire-------------------------------
    void expire(String key, Long time, TimeUnit timeUnit);

    //----------------------delete-------------------------------

    /**
     * 通过key删除 键值对
     *
     * @param key 要删除的key
     * @return 是否删除成功
     */
    Boolean delete(String key);

    /**
     * 批量删除key-value
     *
     * @param keys 要删除的key列表
     * @return 返回删除的数量
     */
    Long multiDelete(List<String> keys) ;

    //----------------------select-------------------------------

    /**
     * 根据key获取键值对
     *
     * @param key key
     * @return 返回value
     */
    <T> Optional<T> get(String key, Class<T> t) ;

    /**
     * 批量获取键值对
     *
     * @param keys 要获取的key列表
     * @return 获取的value 列表
     */
    List<String> get(List<String> keys);

    Object get(String key);

    //----------------------other-------------------------------

    /**
     * @param key key
     * @param number value要增加的数量
     */
    Long increment(String key, Long number);

    /**
     *
     * @param key key
     * @param number value要增加的数量
     * @param time  过期时间
     * @param timeUnit 过期时间单位
     */
    Long increment(String key, Long number, Long time, TimeUnit timeUnit);

    /**
     * @param key key
     */
    Boolean keyExists(String key) ;
}
