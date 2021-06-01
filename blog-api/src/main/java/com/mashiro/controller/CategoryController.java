package com.mashiro.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashiro.common.Result;
import com.mashiro.entity.Blog;
import com.mashiro.entity.Category;
import com.mashiro.service.BlogService;
import com.mashiro.service.CategoryService;
import com.mashiro.vo.BlogInfoVO;
import com.mashiro.vo.PageResultVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 类型管理控制器
 * @Author: BeforeOne
 * @Date: Created in 2021/4/28 15:45
 */

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BlogService blogService;

    @ApiOperation(value = "查看分类列表")
    @GetMapping("/categories")
    public Result listCategories() {
        List<Category> categoryList = categoryService.getCategoryList();
        int count = categoryList.size();
        Map<String, Object> map = new HashMap<>();
        map.put("categoryList", categoryList);
        map.put("count", count);
        return Result.success(map);
    }


}
