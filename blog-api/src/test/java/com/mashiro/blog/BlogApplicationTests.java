package com.mashiro.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class BlogApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
    }
    @Test
    public void hash(){
        String name = "my:hash";
        Map<String,Object> testMap = new HashMap();
        testMap.put("name","jack");
        testMap.put("age",27);
        testMap.put("class","1");
        redisTemplate.opsForHash().putAll(name,testMap);
        //删除某个键值
        System.out.println(redisTemplate.opsForHash().delete(name, "name"));
        //得到某个键值
        System.out.println(redisTemplate.opsForHash().get(name, "age"));
        //得到整个键值
        System.out.println(redisTemplate.opsForHash().entries(name));
        //得到键值的key
        System.out.println(redisTemplate.opsForHash().keys(name));



    }
}
