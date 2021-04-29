package com.shili.mapper;

import com.shili.pojo.Blog;
import com.shili.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 评论持久层接口
 * @Author: BeforeOne
 * @Date: Created in 2021/4/28 14:25
 */

@Mapper
@Repository
public interface CommentMapper {

    /**
    * @Description: 搜寻此博客下的所有评论
    * @param blogId
    * @return {@link List<Comment>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 14:36
    *
    */
    List<Comment> findByBlogId(@Param("blogId") Long blogId);

    /**
    * @Description: 根据博客Id查询没有父评论的评论
    * @param blogId
    * @param blogParentId
    * @return {@link List<Comment>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 14:46
    *
    */
    List<Comment> findByBlogIdAndParentCommentNull(@Param("blogId") Long blogId, @Param("blogParentId") Long blogParentId);

    /**
    * @Description: 根据自己的Id查询自己子类评论
    * @param commentId
    * @return {@link List<Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 14:53
    *
    */
    List<Comment> findSecondaryCommentBySelfId(@Param("commentId") Long commentId);

    /**
    * @Description: 保存评论
    * @param comment
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 17:42
    *
    */
    int saveComment(Comment comment);

    /**
    * @Description: 删除评论
    * @param comment
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 17:43
    *
    */
    int deleteComment(Comment comment);

    /**
    * @Description: 查询自身
    * @param id
    * @return {@link Comment}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 17:48
    *
    */
    Comment findSelfById(@Param("id") Long id);

}
