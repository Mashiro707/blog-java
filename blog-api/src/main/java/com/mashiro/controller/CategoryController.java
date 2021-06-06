package com.mashiro.controller;

import com.mashiro.common.Result;
import com.mashiro.entity.Category;
import com.mashiro.service.BlogService;
import com.mashiro.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "查看分类")
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
