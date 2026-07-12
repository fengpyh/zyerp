package com.cryptomind.config;

import com.cryptomind.trading.utils.LogUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author ryu
 * @version 2018/12/12
 */
@Slf4j
@Component
@AllArgsConstructor
public class RedisServiceImpl implements RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //----------------------save-------------------------------

    @Override
    public void publish(String topic, String message) {
        stringRedisTemplate.convertAndSend(topic,message);
    }

    /**
     * 保存或更新key-value 信息到redis
     *
     * @param key   key
     * @param value value
     */
    public void save(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 带超时时间的保存key-value
     *
     * @param key      key
     * @param value    value
     * @param time     超时时间长度
     * @param timeUnit 超时时间单位
     */
    public void saveWithTimeOut(String key, String value, Long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    /**
     * 保存key-value 若有重复无法保存
     *
     * @param key   key
     * @param value value
     * @return 是否保存成功
     */
    public Boolean saveIfAbsent(String key, String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }


    /**
     * 批量保存,只能保存不存在的key-value
     *
     * @param map 批量要保存的数据
     * @return 是否保存成功
     */
    public Boolean multiSaveIfAbsent(Map<String, String> map) {
        return stringRedisTemplate.opsForValue().multiSetIfAbsent(map);
    }

    /**
     * 批量更新保存key-value
     * @param map
     */
    public void multiupsert(Map<String, String> map) {
        stringRedisTemplate.opsForValue().multiSet(map);
    }

    //----------------------expire-------------------------------

    /**
     *  设置超时时间
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expire(String key, Long time, TimeUnit timeUnit) {
        stringRedisTemplate.expire(key, time, timeUnit);
    }

    //----------------------delete-------------------------------

    /**
     * 通过key删除 键值对
     *
     * @param key 要删除的key
     * @return 是否删除成功
     */
    public Boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * 批量删除key-value
     *
     * @param keys 要删除的key列表
     * @return 返回删除的数量
     */
    public Long multiDelete(List<String> keys) {
        return stringRedisTemplate.delete(keys);
    }

    //----------------------select-------------------------------

    /**
     * 根据key获取键值对
     *
     * @param key key
     * @return 返回value
     */
    public <T> Optional<T> get(String key, Class<T> t) {
        try {
            String o = stringRedisTemplate.boundValueOps(key).get();
            if (StringUtils.isNotBlank(o)) {
                ObjectMapper mapper = new ObjectMapper();
                return Optional.of(mapper.readValue(o, t));
            }

        } catch (Exception e) {
            LogUtil.error(log, LogUtil.LogType.INFO, "get" , "key:{}", key, e);
        }

        return Optional.empty();
    }

    public Object get(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 批量获取键值对
     *
     * @param keys 要获取的key列表
     * @return 获取的value 列表
     */
    public List<String> get(List<String> keys) {
        return stringRedisTemplate.opsForValue().multiGet(keys);
    }


    //----------------------other-------------------------------

    /**
     * @param key key
     * @param number value要增加的数量
     */
    @Override
    public Long increment(String key, Long number) {
        return stringRedisTemplate.opsForValue().increment(key, number);
    }

    /**
     *
     * @param key key
     * @param number value要增加的数量
     * @param time  过期时间
     * @param timeUnit 过期时间单位
     * @return
     */
    @Override
    public Long increment(String key, Long number, Long time, TimeUnit timeUnit) {
        Long v = stringRedisTemplate.opsForValue().increment(key, number);
        stringRedisTemplate.expire(key, time, timeUnit);
        return v;
    }


    /**
     * @param key key
     */
    public Boolean keyExists(String key) {
        return stringRedisTemplate.hasKey(key);
    }

}
