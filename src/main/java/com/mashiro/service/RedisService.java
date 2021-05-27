package com.mashiro.service;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 16:55
 */
public interface RedisService {

    <T> T getObjectByValue(String key, Class t);

    void incrementByKey(String key, int increment);

    void expire(String key, long time);
}
