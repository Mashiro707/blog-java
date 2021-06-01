package com.mashiro.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashiro.common.Result;
import com.mashiro.service.BlogService;
import com.mashiro.service.CategoryService;
import com.mashiro.service.TagService;
import com.mashiro.vo.BlogInfoVO;
import com.mashiro.vo.PageResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    @GetMapping("/tagBlog")
    public Result blogListByTag(@RequestParam(defaultValue = "") String tagName,
                                @RequestParam(defaultValue = "1") Integer pageNum){
        PageResultVO<BlogInfoVO> blogList = blogService.getBlogInfoListByTagNameAndIsPublished(tagName, pageNum);
        Map<String, Object> map = new HashMap<>();
        map.put("blogList", blogList);
        return Result.success(map);
    }

}
