package com.mashiro.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    private List<Blog> blogs = new ArrayList<>();

}
