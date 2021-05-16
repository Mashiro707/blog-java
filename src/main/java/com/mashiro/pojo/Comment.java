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

    private List<Comment> replyComments;

    private Comment parentComment;
    private Long parentCommentId;
    private String parentNickname;

}
