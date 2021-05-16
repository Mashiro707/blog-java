package com.mashiro.controller;

import com.mashiro.pojo.Blog;
import com.mashiro.pojo.Comment;
import com.mashiro.pojo.User;
import com.mashiro.service.BlogService;
import com.mashiro.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: 评论管理控制器
 * @Author: BeforeOne
 * @Date: Created in 2021/4/28 17:03
 */

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    /**
    * @Description: 展示评论
    * @param blogId
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 17:12
    *
    */
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments", commentService.getCommentByBlogId(blogId));
        //model.addAttribute("blog", blogService.getDetailedBlog(blogId));
        return "blog :: commentList";
    }

    /**
    * @Description: 提交评论
    * @param comment
    * @param session
    * @param attributes
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 17:35
    *
    */
    @PostMapping("/comments")
    public String postComment(Comment comment, HttpSession session, RedirectAttributes attributes){
        /*后端校验*/
        if(comment.getContent()=="" || comment.getEmail()=="" || comment.getNickname()=="") {
            attributes.addFlashAttribute("msg", "请填写完整评论信息");
            return "redirect:/comments/" + comment.getBlogId();
        }
        Long blogId = comment.getBlogId();
        comment.setBlog(blogService.getDetailedBlog(blogId));  //绑定博客与评论
        comment.setBlogId(blogId);
        User user = (User) session.getAttribute("user");
        comment.setAvatar(avatar);
        if (user != null) {   //用户为管理员
            if (comment.getNickname().equals(user.getNickname()) && comment.getEmail().equals(user.getEmail())) {
                comment.setAdminComment(true);
                comment.setAvatar(user.getAvatar());
            }
        }
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }

    /**
    * @Description: 删除评论
    * @param blogId
    * @param id
    * @param comment
    * @param attributes
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 17:54
    *
    */
    @GetMapping("/comment/{blogId}/{id}/delete")
    public String delete(@PathVariable Long blogId, @PathVariable Long id, Comment comment, RedirectAttributes attributes, Model model){
        commentService.deleteComment(comment);
        Blog detailedBlog = blogService.getDetailedBlog(blogId);
        List<Comment> comments = commentService.getCommentByBlogId(blogId);
        model.addAttribute("blog", detailedBlog);
        model.addAttribute("comments", comments);
        return "blog";
    }

}
