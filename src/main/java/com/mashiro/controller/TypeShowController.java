package com.mashiro.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashiro.pojo.Blog;
import com.mashiro.pojo.Type;
import com.mashiro.service.BlogService;
import com.mashiro.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: 类型管理控制器
 * @Author: BeforeOne
 * @Date: Created in 2021/4/28 15:45
 */

@Controller
public class TypeShowController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    /**
    * @Description: 通过分类Id展示对应博客列表
    * @param id
    * @param pageNum
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 16:18
    *
    */
    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, @RequestParam(required = false, defaultValue = "1", value = "pageNum")int pageNum,
                        Model model){
        List<Type> types = typeService.getAllTypeAndBlog();
        if (id == -1){
            id = types.get(0).getId();  //默认为列表第一个
        }
        PageHelper.startPage(pageNum,7);
        List<Blog> blogs = blogService.getBlogByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types";
    }

    @PostMapping("/types/id")
    public String typesActiveType(@RequestParam Long id, @RequestParam(required = false, defaultValue = "1", value = "pageNum")int pageNum,
                            Model model){
        List<Type> types = typeService.getAllTypeAndBlog();
        PageHelper.startPage(pageNum,7);
        List<Blog> blogs = blogService.getBlogByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTypeId", id);
        return "types :: typeList";
    }


    /**
    * @Description: 分页
    * @param activeTypeId
    * @param pageNum
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/5/14 23:32
    *
    */
    @PostMapping("/types/page")
    public String typesPage(@RequestParam Long activeTypeId, @RequestParam(required = false, defaultValue = "1", value = "pageNum")int pageNum,
                        Model model){

        List<Type> types = typeService.getAllTypeAndBlog();
        PageHelper.startPage(pageNum,7);
        List<Blog> blogs = blogService.getBlogByTypeId(activeTypeId);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types", types);
        model.addAttribute("pageInfo", pageInfo);
        return "types :: typeBlogList";
    }
}
