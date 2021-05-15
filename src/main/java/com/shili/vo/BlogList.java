package com.shili.vo;

import com.shili.pojo.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 博客列表展示
 * @Author: BeforeOne
 * @Date: Created in 2021/5/14 22:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogList {
    //blog
    private Long id;
    private String title;
    private String firstPicture;
    private Integer views;
    private Date createTime;
    private String description;
    // Type
    private String typeId;
    private String typeName;
    // User
    private String nickName;
    private String avatar;
    //Tag
    private List<Tag> tags;
}
