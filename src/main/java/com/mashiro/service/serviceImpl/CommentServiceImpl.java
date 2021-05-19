package com.mashiro.service.serviceImpl;

import com.mashiro.mapper.CommentMapper;
import com.mashiro.pojo.Comment;
import com.mashiro.service.CommentService;
import com.mashiro.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        //存放找出的所有超级父评论下的回复
        List<Comment> tempReplys = new ArrayList<>();
        //查询出所有超级父评论
        List<Comment> comments = commentMapper.findByBlogIdAndParentCommentNull(blogId, Long.parseLong("-1"));
        for (Comment comment : comments) {
            Long parentId = comment.getId();
            //获取所有超级父评论下的评论
            List<Comment> subComments = commentMapper.findSecondaryCommentBySelfId(parentId);
            if (subComments.size() > 0){
                setReplyCommend(subComments,tempReplys);
                comment.setReplyComments(tempReplys);
                // 初始化
                tempReplys = new ArrayList<>();
            }

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
        Long curId = comment.getParentCommentId();
        if(curId != -1) {
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

    /*判断子回复是否在超级父评论下回复过其他子回复*/
    private void setReplyCommend(List<Comment> subComments,List<Comment> tempReplys){
        if (subComments.size()>0){
            for (Comment subComment:subComments){
                // 获取该子评论是否回复过其他子评论
                // 如果为null 或者 为空字符串，表示没有回复过其他子评论,则不再为其设置子评论对象
                // 除了超级父评论，其他子评论一定会有 replyComment
                Long replyCommentId = subComment.getReplyCommentId();
                if (replyCommentId!=null){
                    // 根据此id设置查询 回复的对象
                    Comment replyCommend = commentMapper.getReplyCommendByReplyCommendId(replyCommentId);
                    subComment.setReplyComment(replyCommend);
                }
                tempReplys.add(subComment);
            }
        }
    }
}
