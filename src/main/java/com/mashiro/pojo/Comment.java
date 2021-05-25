package com.mashiro.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Long id;
    private String nickname; //昵称
    private String email; //邮箱
    private String content; //内容
    private String avatar; //头像
    private boolean adminComment; //是否为博主回复
    private Date createTime; //评论时间
    private boolean replyEmail; //是否接受邮件提示

    private Blog blog;
    private Long blogId;

    private List<Comment> replyComments;

    // 对应的超级父评论
    private Comment parentComment;

    // 超级父评论的ID
    private Long parentCommentId;

    // 回复对应评论的id
    private Long replyCommentId;

    // 对应的父评论对象
    private Comment replyComment;

}
