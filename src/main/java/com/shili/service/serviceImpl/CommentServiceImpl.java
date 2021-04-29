package com.shili.service.serviceImpl;

import com.shili.mapper.CommentMapper;
import com.shili.pojo.Comment;
import com.shili.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 评论业务层实现类
 * @Author: BeforeOne
 * @Date: Created in 2021/4/28 14:26
 */

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {
        List<Comment> comments = commentMapper.findByBlogIdAndParentCommentNull(blogId, Long.parseLong("-1"));
        for (Comment comment : comments) {
            comment.setReplyComments(commentMapper.findSecondaryCommentBySelfId(comment.getId()));
        }
        return comments;
    }

    @Override
    public int saveComment(Comment comment) {
        //防止输入集合为null
        if(comment.getParentCommentId() != null) {
            comment.setParentComment(commentMapper.findSelfById(comment.getParentCommentId()));
        }

        //Union-Find算法（Union操作），若父级评论不是顶级，则向上迭代找到顶级评论作为父评论，只改Id，不改父亲name
        Long curId = comment.getParentComment().getId();
        if(curId != -1) {
            comment.setParentNickname(commentMapper.findSelfById(curId).getNickname());
            while (commentMapper.findSelfById(curId).getParentCommentId() != -1) {
                curId = commentMapper.findSelfById(curId).getParentCommentId();
            }
        }
        //Union更新
        comment.setParentCommentId(curId);
        if(curId == -1)
            comment.setParentComment(null);
        else
            comment.setParentComment(commentMapper.findSelfById(curId));

        //能走到这，说明ParentCommentId和ParentComment已经初始化好了
        comment.setCreateTime(new Date());

        return commentMapper.saveComment(comment);
    }

    @Override
    public int deleteComment(Comment comment) {
        //如果是顶级评论，先删除其子评论，再删除自己
        List<Comment> childComments = commentMapper.findSecondaryCommentBySelfId(comment.getId());
        for(Comment childComment : childComments) {
            commentMapper.deleteComment(childComment);
        }
        return commentMapper.deleteComment(comment);
    }

    @Override
    public List<Comment> findSecondaryCommentBySelfId(Long id) {
        return commentMapper.findSecondaryCommentBySelfId(id);
    }
}
