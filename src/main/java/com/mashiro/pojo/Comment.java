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
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private boolean adminComment;
    private Date createTime;
    private Blog blog;
    private Long blogId;
    private boolean replyEmail;


    private List<Comment> replyComments;

    // 对应的超级父评论
    private Comment parentComment;

    // 超级父评论的ID
    private Long parentCommentId;

    // 回复对应评论的id
    private Long replyCommentId;

    // 对应的父评论对象
    private Comment replyComment;

    //private String parentNickname;

}
