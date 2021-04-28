package com.shili.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shili.pojo.Blog;
import com.shili.pojo.Tag;
import com.shili.pojo.Type;
import com.shili.service.BlogService;
import com.shili.service.TagService;
import com.shili.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: 标签管理控制器
 * @Author: BeforeOne
 * @Date: Created in 2021/4/28 16:05
 */

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    /**
    *
    * @Description: 通过标签Id展示博客列表
    * @param id
    * @param pageNum
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 16:20
    *
    */
    @GetMapping("/tags/{id}")
    public String types(@PathVariable Long id, @RequestParam(required = false, defaultValue = "1", value = "pageNum")int pageNum,
                        Model model){
        PageHelper.startPage(pageNum,7);
        List<Tag> tags = tagService.getAllTagAndBlog();
        if (id == -1){
            id = tags.get(0).getId();  //默认为列表第一个
        }
        List<Blog> blogs = blogService.getBlogByTagId(id);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs);
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
