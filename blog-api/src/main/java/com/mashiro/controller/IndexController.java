package com.mashiro.controller;

import com.mashiro.common.Result;
import com.mashiro.entity.Blog;
import com.mashiro.service.BlogService;
import com.mashiro.service.CategoryService;
import com.mashiro.service.TagService;
import com.mashiro.util.MarkdownUtils;
import com.mashiro.vo.BlogInfoVO;
import com.mashiro.vo.IndexInfoVO;
import com.mashiro.vo.PageResultVO;
import jdk.internal.org.objectweb.asm.Handle;
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
 * @Date: Created in 2021/5/31 18:26
 */
@RestController
public class IndexController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public Result Index(@RequestParam(defaultValue = "1") Integer pageNum){
        //网站信息
        IndexInfoVO indexInfo = blogService.getIndexInfo();
        //博客列表
        PageResultVO<BlogInfoVO> blogList = blogService.getBlogInfoListByIsPublished(pageNum);
        Map<String, Object> map = new HashMap<>();
        map.put("indexInfo", indexInfo);
        map.put("blogList", blogList);
        return Result.success(map);

    }





}
