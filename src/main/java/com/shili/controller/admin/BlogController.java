package com.shili.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shili.pojo.Blog;
import com.shili.pojo.User;
import com.shili.service.BlogService;
import com.shili.service.TagService;
import com.shili.service.TypeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: 博客控制管理器
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 21:10
 */

@Controller
@RequestMapping("/admin")
public class BlogController {

    /*注入业务层接口*/
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /**
    *
    * @Description: 封装获取类型和标签方法
    * @param model
    * @return
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 21:38
    *
    */
    private void setTypeAndTag(Model model) {
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
    }

    /**
    *
    * @Description: 后台展示博客
    * @param pageNum
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 21:43
    *
    */
    @GetMapping("/blogs")
    public String blogs(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, Model model){
        PageHelper.startPage(pageNum, 5);
        List<Blog> listBlog = blogService.getAllBlog();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(listBlog);
        model.addAttribute("pageInfo", pageInfo);
        setTypeAndTag(model);
        return "admin/blogs";
    }

    /**
    *
    * @Description: 后台根据条件（标题、分类、是否推荐）查询博客
    * @param blog
    * @param pageNum
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/26 8:49
    *
    */
    @PostMapping("/blogs/search")
    public String searchBlogs(Blog blog, @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<Blog> allBlogs = blogService.searchAllBlog(blog);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(allBlogs);
        model.addAttribute("pageInfo",pageInfo);
        setTypeAndTag(model);
        return "admin/blogs :: blogList";
    }

    /**
    *
    * @Description: 跳转至新增页面
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/26 8:56
    *
    */
    @GetMapping("/blogs/input")
    public String toInputBlog(Model model) {
        model.addAttribute("blog", new Blog());
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    /**
    *
    * @Description: 根据ID查询博客并且进行编辑
    * @param id
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/26 9:04
    *
    */
    @GetMapping("/blogs/{id}/input")
    public String toEditBolg(@PathVariable Long id, Model model){
        Blog blog = blogService.getBlogById(id);
        /*将tags集合转换为tagIds字符串*/
        blog.init();
        model.addAttribute("blog", blog);
        setTypeAndTag(model);
        return "admin/blogs-input";
    }

    /**
    *
    * @Description: 新增博客
    * @param blog
    * @param session
    * @param attributes
    * @return {@link java.lang.String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/26 9:25
    *
    */
    @PostMapping("/blogs")
    public String inputBlog(Blog blog, HttpSession session, RedirectAttributes attributes){
        /*无论是编辑还是新增，都需要重置所有值，即使相同也重置*/
        blog.setUser((User) session.getAttribute("user"));
        /*设置用户ID*/
        blog.setUserId(blog.getUser().getId());
        /*设置用户view*/
        blog.setViews(0);
        /*设置博客分类*/
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        /*设置博客分类ID值*/
        blog.setTypeId(blog.getType().getId());
        /*设置博客标签*/
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        blogService.createBlog(blog);
        attributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/admin/blogs";
    }

    /**
    *
    * @Description: 编辑博客
    * @param blog
    * @param session
    * @param attributes
    * @return {@link java.lang.String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/26 9:27
    *
    */
    @PostMapping("/blogs/{id}")
    public String updateBlog(Blog blog, HttpSession session, RedirectAttributes attributes){
        //无论是编辑还是新建，都会要重置所有属性值，就算是相同的也应该要重置
        blog.setUser((User)session.getAttribute("user"));
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        //设置用户views
        blog.setViews(blog.getViews());
        //设置blog的type
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //给blog中的List<Tag>赋值
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        blogService.updateBlog(blog);
        attributes.addFlashAttribute("msg", "更新成功");
        return "redirect:/admin/blogs";
    }

    /**
    *
    * @Description: 后台根据id删除博客
    * @param id
    * @param attributes
    * @return {@link java.lang.String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/26 10:03
    *
    */
    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/blogs";
    }
}
