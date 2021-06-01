/*
package com.mashiro.controller;

import com.mashiro.entity.Blog;
import com.mashiro.entity.Comment;
import com.mashiro.entity.User;
import com.mashiro.service.BlogService;
import com.mashiro.service.CommentService;
import com.mashiro.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

*/
/**
 * @Description: 评论管理控制器
 * @Author: BeforeOne
 * @Date: Created in 2021/4/28 17:03
 *//*


@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private EmailService emailService;

    @Value("${comment.avatar}")
    private String avatar;

    */
/**
    * @Description: 展示评论
    * @param blogId
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 17:12
    *
    *//*

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments", commentService.getCommentByBlogId(blogId));
        //model.addAttribute("blog", blogService.getDetailedBlog(blogId));
        return "blog :: commentList";
    }

    */
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
    *//*

    @PostMapping("/comments")
    public String postComment(Comment comment, HttpSession session, RedirectAttributes attributes) throws MessagingException {
        */
/*后端校验*//*

        if(comment.getContent()=="" || comment.getEmail()=="" || comment.getNickname()=="") {
            attributes.addFlashAttribute("msg", "请填写完整评论信息");
            return "redirect:/comments/" + comment.getBlogId();
        }
        Long blogId = comment.getBlogId();
        comment.setBlog(blogService.getDetailedBlog(blogId));  //绑定博客与评论
        comment.setBlogId(blogId);
        comment.setAvatar(avatar);
        User user = (User) session.getAttribute("user");
        if (user != null) {   //用户为管理员
            if (comment.getNickname().equals(user.getNickname()) && comment.getEmail().equals(user.getEmail())) {
                comment.setAdminComment(true);
                comment.setAvatar(user.getAvatar());
            }
        }else{
            // 判断邮箱是否为qq邮箱
            if (comment.getEmail().trim().toLowerCase().contains("@qq.com")){
                String regEx = "[^0-9]";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(comment.getEmail());
                comment.setAvatar("http://q1.qlogo.cn/g?b=qq&nk="+m.replaceAll("").trim()+"&s=100");
            }else {
                // 如果不是正确的qq邮箱，使用默认头像
                comment.setAvatar(avatar);
            }
        }
        commentService.saveComment(comment);
        //判断是否为超级父评论,如果是超级父评论则默认向管理员发送邮件通知
        if (comment.getParentCommentId() == -1 && comment.getReplyCommentId()==null){
            emailService.sendTemplateMail(comment);
         //判断是否 是给超级父评论评论，如果是给超级父评论 评论的话，那么判断超级父评论是否开启邮件通知功能
        }else if(comment.getReplyCommentId()==null && comment.getParentComment().isReplyEmail()){
            emailService.sendTemplateMail(comment);
        }else if(comment.getReplyCommentId()!=null && comment.getReplyComment().isReplyEmail()){
            emailService.sendTemplateMail(comment);
        }else if (comment.getReplyComment().isAdminComment()){
            emailService.sendTemplateMail(comment);
        }
            //emailService.sendTemplateMail(comment);
        return "redirect:/comments/" + blogId;
    }

    */
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
    *//*

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
*/
