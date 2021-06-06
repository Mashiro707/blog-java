package com.mashiro.controller;


import com.mashiro.annotation.VisitLogger;
import com.mashiro.common.Result;
import com.mashiro.service.BlogService;
import com.mashiro.service.CategoryService;
import com.mashiro.service.TagService;
import com.mashiro.util.StringUtils;
import com.mashiro.vo.*;
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
 * @Date: Created in 2021/5/31 14:55
 */
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

    /**
    * 根据分类名称查询博客列表
    * @param categoryName
    * @param pageNum
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/31 18:06
    */
    @VisitLogger(behavior = "查看分类")
    @GetMapping("/categoriesBlog")
    public Result blogListByCategory(@RequestParam(defaultValue = "") String categoryName,
                                     @RequestParam(defaultValue = "1") Integer pageNum){
        PageResultVO<BlogInfoVO> blogList = blogService.getBlogInfoListByCategoryNameAndIsPublished(categoryName, pageNum);
        Map<String, Object> map = new HashMap<>();
        map.put("blogList", blogList);
        return Result.success(map);
    }

    /**
    * 根据标签名称获取博客列表
    * @param tagName
    * @param pageNum
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/31 19:21
    */
    @VisitLogger(behavior = "查看标签")
    @GetMapping("/tagBlog")
    public Result blogListByTag(@RequestParam(defaultValue = "") String tagName,
                                @RequestParam(defaultValue = "1") Integer pageNum){
        PageResultVO<BlogInfoVO> blogList = blogService.getBlogInfoListByTagNameAndIsPublished(tagName, pageNum);
        Map<String, Object> map = new HashMap<>();
        map.put("blogList", blogList);
        return Result.success(map);
    }

    @VisitLogger(behavior = "查看博客")
    @GetMapping("/blogDetail")
    public Result getBlogDetail(@RequestParam Long id){
        BlogDetailVO blog = blogService.getBlogByIdAndIsPublished(id);
        blogService.updateViewsToRedis(id);
        return Result.success(blog);
    }

    @GetMapping("/articlesnewest")
    public Result getBlogNewest(){
        List<NewBlogVO> blogList = blogService.getNewBlogListByIsPublished();
        Map<String, Object> map = new HashMap<>();
        map.put("blogList", blogList);
        return Result.success(map);
    }

    /**
    * 按关键字根据文章内容搜索公开的博客文章
    * @param keywords 搜索关键字
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/6/3 21:51
    */
    @VisitLogger(behavior = "搜索博客")
    @GetMapping("/search")
    public Result search(@RequestParam String keywords){
        //校验关键字字符串合法性
        if (StringUtils.isEmpty(keywords) || StringUtils.hasSpecialChar(keywords) || keywords.trim().length() > 20) {
            return Result.error("参数错误");
        }
        List<SearchBlogVO> searchBlogs = blogService.getSearchBlogListByQueryAndIsPublished(keywords.trim());
        Map<String, Object> map = new HashMap<>();
        map.put("searchBlogs", searchBlogs);
        return Result.success(map);
    }
}
