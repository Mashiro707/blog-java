/*
package com.mashiro.service.Impl;

import com.mashiro.mapper.BlogMapper;
import com.mashiro.mapper.CommentMapper;
import com.mashiro.entity.Blog;
import com.mashiro.entity.Comment;
import com.mashiro.entity.Email;
import com.mashiro.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

*/
/**
 * @Description: 评论邮件提醒实现类
 * @Author: BeforeOne
 * @Date: Created in 2021/5/17 21:35
 *//*

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private TemplateEngine templateEngine;

    */
/**
    * @Description: 发送HTML邮件
    * @param to
    * @param subject
    * @param content
    * @return
    * @throws
    * @author BeforeOne
    * @data 2021/5/17 21:42
    *
    *//*

    @Transactional
    @Override
    public void sendHtmlMail(String to, String subject, String content){
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content,true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (MailException e) {
            e.printStackTrace();
        }

    }

    */
/**
    * @Description: HTML引擎模版
    * @param comment
    * @return
    * @throws
    * @author BeforeOne
    * @data 2021/5/19 20:47
    *
    *//*

    @Transactional
    @Async
    @Override
    public void sendTemplateMail(Comment comment){
        Email email = new Email();
        Blog blog = blogMapper.getBlogById(comment.getBlogId());
        String title = "小白博客评论回复";
        email.setNickname(comment.getNickname());
        email.setContent(comment.getContent());
        email.setBlogId(comment.getBlogId());
        email.setBlogName(blog.getTitle());
        email.setReplyFlag(true);

        */
/*
            判断评论是否回复其他评论
                如果有，直接给被回复的评论发送邮件
                如果没有，判断是否为主评论
                    如果不是主评论，给主评论发送邮件
                    如果是主评论，判断是否为管理员评论
                        不是管理员评论，发送邮件给管理员
                        是管理员评论，不用发送邮件
        *//*

        if (null!=comment.getReplyCommentId()){
            Comment replyComment = commentMapper.getReplyCommendByReplyCommendId(comment.getReplyCommentId());
            email.setReplyNickname(replyComment.getNickname());
            email.setReplyContent(replyComment.getContent());
            email.setReplyEmail(replyComment.getEmail());
            //创建邮件正文
            Context context = new Context();
            context.setVariable("email",email);
            String emailContent = templateEngine.process("email/emailTemplate-comment", context);
            sendHtmlMail(replyComment.getEmail(),title,emailContent);
        }else {
            if (-1!=comment.getParentCommentId()){
                Comment replyComment = commentMapper.getReplyCommendByReplyCommendId(comment.getParentCommentId());
                email.setReplyNickname(replyComment.getNickname());
                email.setReplyContent(replyComment.getContent());
                email.setReplyEmail(replyComment.getEmail());
                //创建邮件正文
                Context context = new Context();
                context.setVariable("email",email);
                String emailContent = templateEngine.process("email/emailTemplate-comment", context);
                sendHtmlMail(replyComment.getEmail(),title,emailContent);
            }else {
                if (!comment.isAdminComment()){
                    email.setReplyFlag(false);
                    //创建邮件正文
                    Context context = new Context();
                    context.setVariable("email",email);
                    String emailContent = templateEngine.process("email/emailTemplate-comment", context);
                    sendHtmlMail(from,title,emailContent);
                }
            }
        }
    }
}
*/
