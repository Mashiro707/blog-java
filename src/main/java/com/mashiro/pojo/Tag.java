package com.mashiro.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    private Long id;
    private String name; //标签名称

    private List<Blog> blogs = new ArrayList<>(); //该标签下的博客

}
