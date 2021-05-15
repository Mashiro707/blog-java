package com.shili.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shili.pojo.Blog;
import com.shili.pojo.Comment;
import com.shili.pojo.Tag;
import com.shili.pojo.Type;
import com.shili.service.BlogService;
import com.shili.service.CommentService;
import com.shili.service.TagService;
import com.shili.service.TypeService;
import com.shili.vo.BlogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: 首页控制管理器
 * @Author: BeforeOne
 * @Date: Created in 2021/4/28 13:07
 */

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;

    /**
    * @Description: 首页博客展示
    * @param pageNum
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 13:08
    *
    */
    @GetMapping("/")
    public String index(@RequestParam(required = false, defaultValue = "1", value = "pageNum")int pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<Blog> allBlog = blogService.getIndexBlog();
        /*获取博客的类型(联表查询)(首页右侧类型栏，每个类型多少博客blogs.size())*/
        List<Type> allType = typeService.getFiveTypeAndBlog();
        /*获取博客的标签(同类型原理)*/
        List<Tag> allTag = tagService.getAllTagAndBlog();
        /*右侧显示七条最新推荐博客*/
        List<Blog> recommendBlog = blogService.getAllRecommendBlog();
        if(recommendBlog.size() > 7)
            recommendBlog = blogService.getPortRecommendBlog();

        PageInfo<Blog> pageInfo = new PageInfo<Blog>(allBlog);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",allType);
        model.addAttribute("tags",allTag);
        model.addAttribute("recommendBlogs",recommendBlog);
        return "index";
    }

    /**
    * @Description: 页面局部刷新
    * @param pageNum
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/5/14 21:53
    *
    */
    @PostMapping("/")
    public String indexPage(@RequestParam(required = false, defaultValue = "1", value = "pageNum")int pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<Blog> allBlog = blogService.getIndexBlog();
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(allBlog);
        model.addAttribute("pageInfo",pageInfo);
        return "index :: indexBlogList";
    }


    /**
    * @Description: 全局搜索
    * @param pageNum
    * @param query
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 18:11
    *
    */
    @GetMapping("/search")
    public String search(@RequestParam(required = false, defaultValue = "1", value = "pageNum")int pageNum,
                         @RequestParam String query,
                         Model model){
        PageHelper.startPage(pageNum,2);
        List<Blog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(searchBlog);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "search";
    }

    /**
    * @Description: 页面局部刷新
    * @param pageNum
    * @param query
    * @param model
    * @return {@link java.lang.String}
    * @throws
    * @author BeforeOne
    * @data 2021/5/14 21:52
    *
    */
    @PostMapping("/search")
    public String searchPage(@RequestParam(required = false, defaultValue = "1", value = "pageNum")int pageNum,
                         @RequestParam String query,
                         Model model){
        PageHelper.startPage(pageNum,2);
        List<Blog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(searchBlog);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("query",query);
        return "search :: searchBlogList";
    }

    /**
    * @Description: 博客详情
    * @param id
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 18:11
    *
    */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        Blog blog = blogService.getDetailedBlog(id);
        List<Comment> comments = commentService.getCommentByBlogId(id);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", blog);
        return "blog";
    }

    /**
    * @Description: 底部最新博客(已过时)
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 18:12
    *
    */
    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs",blogService.getThreeRecommendBlog());
        return "_fragments :: newblogList";
    }

    /**
    * @Description: 登出
    * @param session
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/5/12 14:37
    *
    */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        System.out.println("执行用户登出操作");
        session.removeAttribute("user");
        return "redirect:/";
    }

    /**
    * @Description: 底部博客信息统计（后期可以用Redis实现）
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/5/12 14:41
    *
    */
    @GetMapping("/footer/blogInfo")
    public String blogInfo(Model model){
        System.out.println("获取博客信息统计");
        BlogInfoVo blogInfoVo = blogService.getBlogInfo();
        model.addAttribute("blogInfo", blogInfoVo);

        return "_fragments :: blogInfo";
    }
}
