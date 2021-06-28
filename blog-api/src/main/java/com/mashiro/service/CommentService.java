package com.mashiro.service;

import com.mashiro.dto.CommentDTO;
import com.mashiro.entity.Comment;
import com.mashiro.vo.PageCommentVO;

import java.util.List;

/**
 * @Description: 评论业务层接口
 * @Author: Mashiro
 * @Date: Created in 2021/4/28 14:25
 */
public interface CommentService {

    /**
    * 新增评论
    * @param commentDTO
    * @return
    * @author Mashiro
    * @data 2021/5/28 22:46
    *
    */
    void saveComment(CommentDTO commentDTO);

    /**
    * 通过评论Id删除评论
    * @param commentId 评论Id
    * @return
    * @author Mashiro
    * @data 2021/5/28 22:47
    *
    */
    void deleteCommentById(Long commentId);

    /**
    * 通过博客Id删除评论
    * @param blogId
    * @return
    * @author Mashiro
    * @data 2021/5/28 22:47
    *
    */
    void deleteCommentsByBlogId(Long blogId);

    /**
    * 修改评论
    * @param comment 评论对象
    * @return
    * @author Mashiro
    * @data 2021/5/28 22:47
    *
    */
    void updateComment(Comment comment);

    /**
    * 更新评论公开状态
    * @param commentId 评论 id
    * @param published  是否公开
    * @return
    * @author Mashiro
    * @data 2021/5/28 22:48
    *
    */
    void updateCommentPublishedById(Long commentId, Boolean published);

    /**
    * 更新评论通知状态
    * @param commentId 评论 id
    * @param notice 是否开启邮件通知
    * @return
    * @author Mashiro
    * @data 2021/5/28 22:49
    *
    */
    void updateCommentNoticeById(Long commentId, Boolean notice);

    /**
    * 通过 id 获取评论
    * @param id 评论 id
    * @return {@link Comment}
    * @author Mashiro
    * @data 2021/5/28 22:50
    *
    */
    Comment getCommentById(Long id);

    List<Comment> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

    List<PageCommentVO> getPageCommentList(Integer page, Long blogId, Long parentCommentId);

    List<PageCommentVO> getReplyCommentList(Long parentCommentId);

    /**
    * 统计页面公开的评论
    * @param page 页面
    * @param blogId 博客 id
    * @return {@link int}
    * @author Mashiro
    * @data 2021/5/28 22:50
    *
    */
    int countByPageAndIsPublished(Integer page, Long blogId);

}
