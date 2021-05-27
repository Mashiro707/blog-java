package com.mashiro.service.Impl;

import com.mashiro.service.RedisService;
import com.mashiro.util.JacksonUtils;
import com.mashiro.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 16:56
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public <T> T getObjectByValue(String key, Class t) {
        Object redisResult = redisUtils.get(key);
        T object = (T) JacksonUtils.convertValue(redisResult, t);
        return object;
    }

    @Override
    public void incrementByKey(String key, int increment) {
        if (increment < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        redisUtils.incr(key, increment);
    }

    @Override
    public void expire(String key, long time) {
        redisUtils.expire(key, time);
    }
}
