package com.mashiro.controller;

import com.mashiro.common.Result;
import com.mashiro.entity.Tag;
import com.mashiro.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "标签模块")
@RestController
public class TagController {
    @Autowired
    private TagService tagService;

    @ApiOperation(value = "标签列表")
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
