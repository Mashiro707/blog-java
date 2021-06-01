package com.mashiro.controller;

import com.mashiro.common.Result;
import com.mashiro.entity.Tag;
import com.mashiro.service.BlogService;
import com.mashiro.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/31 17:47
 */
@RestController
public class TagController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tags")
    public Result tagList(){
        List<Tag> tagList = tagService.getTagList();
        int count = tagList.size();
        Map<String, Object> map = new HashMap<>();
        map.put("tags", tagList);
        map.put("count", count);
        return Result.success(map);
    }
}
