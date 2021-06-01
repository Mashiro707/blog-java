package com.mashiro.mapper;

import com.mashiro.dto.CommentDTO;
import com.mashiro.entity.Blog;
import com.mashiro.entity.Comment;
import com.mashiro.vo.PageCommentVO;
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
     * 保存评论
     * @param commentDTO 评论DTO对象
     * @return {@link int}
     * @throws
     * @author BeforeOne
     * @data 2021/4/28 17:42
     *
     */
    int saveComment(CommentDTO commentDTO);

    /**
     * 通过评论id删除评论
     * @param commentId  评论id
     * @return {@link int}
     * @throws
     * @author BeforeOne
     * @data 2021/4/28 17:43
     *
     */
    int deleteCommentById(Long commentId);

    /**
    * 根据博客Id删除评论
    * @param blogId  博客Id
    * @return {@link int}
    * @author Mashiro
    * @data 2021/5/28 22:15
    *
    */
    int deleteCommentsByBlogId(Long blogId);

    /**
    * 更新评论
    * @param comment 评论对象
    * @return {@link int}
    * @author Mashiro
    * @data 2021/5/28 22:17
    *
    */
    int updateComment(Comment comment);

    /**
    * 更新评论公开状态
    * @param commentId 评论Id
    * @return {@link int}
    * @author Mashiro
    * @data 2021/5/28 22:20
    *
    */
    int updateCommentPublishedById(Long commentId, Boolean published);

    /**
    * 更新评论接收邮件提醒状态
    * @param commentId 评论Id
    * @param notice 是否开启邮件通知
    * @return {@link int}
    * @author Mashiro
    * @data 2021/5/28 22:21
    *
    */
    int updateCommentNoticeById(Long commentId, Boolean notice);

    /**
    * 按id查询评论
    * @param id 评论Id
    * @return {@link Comment}
    * @author Mashiro
    * @data 2021/5/28 22:38
    *
    */
    Comment getCommentById(Long id);

    /**
    * 按页面和父评论id查询评论List
    * @param page 所属页面
    * @param blogId 博客Id
    * @param parentCommentId 父评论Id
    * @return {@link List< Comment>}
    * @author Mashiro
    * @data 2021/5/28 22:38
    *
    */
    List<Comment> getListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

    /**
    * 按父评论id查询评论List
    * @param parentCommentId 父评论Id
    * @return {@link List< Comment>}
    * @author Mashiro
    * @data 2021/5/28 22:41
    *
    */
    List<Comment> getListByParentCommentId(Long parentCommentId);

    /**
    * 查询页面展示的评论List
    * @param page 所属页面
    * @param blogId 评论Id
    * @param parentCommentId 父评论Id
    * @return {@link List< PageCommentVO>}
    * @author Mashiro
    * @data 2021/5/28 22:41
    *
    */
    List<PageCommentVO> getPageCommentListByPageAndParentCommentId(Integer page, Long blogId, Long parentCommentId);

    /**
     * 获取总评论数目
     * @param
     * @return {@link Integer}
     * @throws
     * @author BeforeOne
     * @data 2021/5/12 14:50
     *
     */
    Integer countComment();

    /**
    * 按页面查询评论数量
    * @param page 所属页面
    * @param blogId 博客Id
    * @return {@link Integer}
    * @author Mashiro
    * @data 2021/5/28 22:42
    *
    */
    Integer countByPageAndIsPublished(Integer page, Long blogId);
}
