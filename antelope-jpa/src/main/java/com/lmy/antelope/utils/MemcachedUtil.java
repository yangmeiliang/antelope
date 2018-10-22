package com.lmy.antelope.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.MemcachedClient;

import java.util.Optional;

/**
 * @author yangmeiliang
 * @date 2018/8/15
 */
@Slf4j
public class MemcachedUtil {

    /**
     * key 默认过期时间  单位：秒
     */
    private static final int DEFAULT_EXPIRE_TIME = 60 * 30;
    /**
     * openclass项目缓存key前缀
     */
    private static final String CACHE_KEY_PREFIX = "openclass:";

    public static MemcachedClient client = SpringUtil.getBean(MemcachedClient.class);

    public static String get(String key) {
        try {
            key = CACHE_KEY_PREFIX + key;
            return client.get(key);
        } catch (Exception e) {
            log.error("cache get error, key: {}", key, e);
        }
        return null;
    }

    public static boolean set(String key, String value, Integer expireTime) {
        try {
            key = CACHE_KEY_PREFIX + key;
            return client.set(key, expireTime, value);
        } catch (Exception e) {
            log.error("cache set error, key: {}, value: {}, expireTime: {}", key, value, expireTime, e);
        }
        return false;
    }

    public static <T> T get(String key, Class<T> clazz) {
        return Optional.ofNullable(get(key)).map(value -> JSON.parseObject(value, clazz)).orElse(null);
    }

    public static boolean set(String key, String value) {
        return set(key, value, DEFAULT_EXPIRE_TIME);
    }

    public static boolean set(String key, Object value) {
        return set(key, value, DEFAULT_EXPIRE_TIME);
    }

    public static boolean set(String key, Object value, Integer expireTime) {
        return set(key, JSON.toJSONString(value), expireTime);
    }

    public static long incr(String key, Integer incrValue) {
        return incr(key, incrValue, 0);
    }

    public static long incr(String key, Integer incrValue, Integer initValue) {
        try {
            key = CACHE_KEY_PREFIX + key;
            return client.incr(key, incrValue, initValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long decr(String key, Integer decrValue) {
        return decr(key, decrValue, 0);
    }

    public static long decr(String key, Integer decrValue, Integer initValue) {
        try {
            key = CACHE_KEY_PREFIX + key;
            return client.decr(key, decrValue, initValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean del(String key) {
        try {
            key = CACHE_KEY_PREFIX + key;
            return client.delete(key);
        } catch (Exception e) {
            log.error("cache del error, key: {}", key, e);
        }
        return false;
    }


}
