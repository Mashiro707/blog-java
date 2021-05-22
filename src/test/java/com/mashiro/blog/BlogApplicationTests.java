package com.mashiro.blog;

import com.mashiro.util.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BlogApplicationTests {
    @Autowired
    RedisUtils redisUtils;
    @Test
    void contextLoads() {
    }

    @Test
    public void test1(){
        System.out.println(redisUtils.get("redisTest"));
    }
}
