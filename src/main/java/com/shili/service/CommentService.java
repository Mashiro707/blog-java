package com.shili.service;

import com.shili.pojo.Comment;

import java.util.List;

/**
 * @Description: 评论业务层接口
 * @Author: BeforeOne
 * @Date: Created in 2021/4/28 14:25
 */
public interface CommentService {

    /**
    * @Description: 通过博客Id获取评论
    * @param blogId
    * @return {@link List< Comment>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 14:44
    *
    */
    List<Comment> getCommentByBlogId(Long blogId);

    /**
    * @Description: 保存评论
    * @param comment
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 17:53
    *
    */
    int saveComment(Comment comment);

    /**
    * @Description: 删除评论
    * @param comment
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 17:53
    *
    */
    int deleteComment(Comment comment);

    /**
    * @Description: 输入自己的Id查询自己的二级评论
    * @param id
    * @return {@link List< Comment>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 17:53
    *
    */
    public List<Comment> findSecondaryCommentBySelfId(Long id);

}
