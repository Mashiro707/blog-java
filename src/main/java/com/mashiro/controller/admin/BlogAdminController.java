package com.mashiro.controller.admin;


import com.mashiro.service.BlogService;
import com.mashiro.service.CategoryService;
import com.mashiro.service.CommentService;
import com.mashiro.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/28 16:49
 */
@RestController
@RequestMapping("/admin")
public class BlogAdminController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

}
