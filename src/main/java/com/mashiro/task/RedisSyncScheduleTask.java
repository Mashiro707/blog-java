package com.mashiro.task;

import com.mashiro.common.RedisKey;
import com.mashiro.service.BlogService;
import com.mashiro.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/28 17:13
 */
@Component
public class RedisSyncScheduleTask {

    @Autowired
    private BlogService blogService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 从Redis同步博客文章浏览量到数据库
     */
    public void syncBlogViewsToDatabase() {
        String redisKey = RedisKey.BLOG_VIEWS_MAP;
        Map blogViewsMap = redisUtils.getMapByHash(redisKey);
        Set<Integer> keys = blogViewsMap.keySet();
        for (Integer key : keys) {
            Integer views = (Integer) blogViewsMap.get(key);
            blogService.updateViews(key.longValue(), views);
        }
    }
}
