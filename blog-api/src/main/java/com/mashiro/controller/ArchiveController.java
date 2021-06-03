package com.mashiro.controller;

import com.mashiro.common.Result;
import com.mashiro.service.BlogService;
import com.mashiro.vo.ArchiveInfoVO;
import com.mashiro.vo.PageResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/6/3 19:42
 */
@RestController
public class ArchiveController {

    @Autowired
    BlogService blogService;

    @GetMapping("/archives")
    public Result archives(@RequestParam(defaultValue = "1") Integer pageNum) {
        int i = blogService.countBlogByIsPublished();
        PageResultVO<ArchiveInfoVO> archiveBlog = blogService.getArchiveBlog(pageNum);
        Map<String, Object> map = new HashMap<>();
        map.put("count", i);
        map.put("archiveList", archiveBlog);
        return Result.success(map);
    }
}
