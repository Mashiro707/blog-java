package com.mashiro.service.Impl;

import com.mashiro.common.RedisKey;
import com.mashiro.entity.About;
import com.mashiro.exception.PersistenceException;
import com.mashiro.mapper.AboutMapper;
import com.mashiro.service.AboutService;
import com.mashiro.service.RedisService;
import com.mashiro.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 关于我页面业务层实现
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 14:10
 */
@Service
public class AboutServiceImpl implements AboutService {

    private final AboutMapper aboutMapper;
    private final RedisService redisService;

    public AboutServiceImpl(AboutMapper aboutMapper, RedisService redisService) {
        this.aboutMapper = aboutMapper;
        this.redisService = redisService;
    }

    @Override
    public void updateAbout(Map<String, String> map) {
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            updateOneAbout(key, map.get(key));
        }
        deleteAboutRedisCache();
    }

    @Override
    public boolean getAboutCommentEnabled() {
        String commentEnabledString = aboutMapper.getAboutCommentEnabled();
        return Boolean.parseBoolean(commentEnabledString);
    }

    @Override
    public Map<String, String> getAboutInfo() {
        //首先尝试从redis缓存中获取
        String redisKey = RedisKey.ABOUT_INFO_MAP;
        Map<String, String> aboutInfoMapFromRedis = redisService.getMapByValue(redisKey);
        if (aboutInfoMapFromRedis != null) {
            return aboutInfoMapFromRedis;
        }
        //从数据库中获取
        List<About> abouts = aboutMapper.getList();
        Map<String, String> aboutInfoMap = new HashMap<>();
        for (About about : abouts) {
            //判断是否为markdown
            if ("content".equals(about.getNameEn())) {
                about.setValue(MarkdownUtils.markdownToHtmlExtensions(about.getValue()));
            }
            aboutInfoMap.put(about.getNameEn(), about.getValue());
        }
        redisService.saveMapToValue(redisKey, aboutInfoMap);
        return aboutInfoMap;
    }

    @Override
    public Map<String, String> getAboutSetting() {
        List<About> abouts = aboutMapper.getList();
        Map<String, String> map = new HashMap<>();
        for (About about : abouts) {
            map.put(about.getNameEn(), about.getValue());
        }
        return map;
    }

    @Transactional
    public void updateOneAbout(String nameEn, String value) {
        if (aboutMapper.updateAbout(nameEn, value) != 1) {
            throw new PersistenceException("修改失败");
        }
    }

    //删除缓存
    private void deleteAboutRedisCache() {
        redisService.deleteCacheByKey(RedisKey.ABOUT_INFO_MAP);
    }
}
