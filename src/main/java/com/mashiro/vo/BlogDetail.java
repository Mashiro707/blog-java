package com.mashiro.vo;

import com.mashiro.pojo.Comment;
import com.mashiro.pojo.Tag;
import com.mashiro.pojo.Type;
import com.mashiro.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 博客详情
 * @Author: BeforeOne
 * @Date: Created in 2021/5/24 21:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDetail {
    private Long id;
    private String title; //标题
    private String content; //内容
    private String firstPicture; //首图
    private String flag; //标记类型
    private Integer views; //浏览次数
    private boolean appreciation; //赞赏开关
    private boolean shareStatement; //转载声明开关
    private boolean commentabled; //评论开关
    private boolean published; //发布
    private boolean recommend; //推荐开关
    private Boolean top; //是否置顶
    private Date createTime; //创建时间
    private Date updateTime; //更新时间

    private Type type; //文章分类
    private List<Tag> tags = new ArrayList<>(); //文章标签

    /**
    private Integer views;//浏览次数
	private Integer words;//文章字数
	private Integer readTime;//阅读时长(分钟)
	private String password;//密码保护
	*/
}
