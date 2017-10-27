package com.jingyuu.ershoujing.service.support.impl;

import com.jingyuu.ershoujing.service.support.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author owen
 * @date 2017-10-26
 */
@Component
public class RedisServiceImpl<T> implements RedisService<T> {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void put(String key, T domain, long expire) {
        redisTemplate.opsForValue().set(key, domain);
        if (expire != -1) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    @Override
    public void put(String key, T domain) {
        this.put(key, domain, -1);
    }


    @Override
    public void hset(String key, String field, T value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    @Override
    public T get(String key) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    @Override
    public T hget(String key, String field) {
        return (T) redisTemplate.opsForHash().get(key, field);
    }
}
