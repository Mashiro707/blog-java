package com.mashiro.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    private Long id;
    private String title; //标题
    private String content; //内容
    private String firstPicture; //首图
    private String flag; //标记
    private Integer views; //浏览次数
    private boolean appreciation; //赞赏开关
    private boolean shareStatement; //转载声明开关
    private boolean commentabled; //评论开关
    private boolean published; //发布
    private boolean recommend; //推荐开关
    private Date createTime; //创建时间
    private Date updateTime; //更新时间
    //这个属性用来在mybatis中进行连接查询的
    private Long typeId;
    private Long userId;

    //类型 多个对一
    private Type type;
    //标签 多对多
    private List<Tag> tags = new ArrayList<>();

    private User user;

    private List<Comment> comments = new ArrayList<>();

    private String tagIds;
    /*描述*/
    private String description;


    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    private String tagsToIds(List<Tag> tags){
        if (!tags.isEmpty()){
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags){
                if (flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }
}
