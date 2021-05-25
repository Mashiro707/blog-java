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
    private String nickname; //用户昵称
    private String username; //用户名
    private String password; //密码
    private String email; //邮箱
    private String avatar; //头像地址
    private String role; //用户权限

    private Date createTime; //创建时间

    private Date updateTime; //更新时间

    private List<Blog> blogs = new ArrayList<>();

}
