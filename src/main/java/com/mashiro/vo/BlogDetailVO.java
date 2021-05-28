package com.mashiro.vo;

import com.mashiro.entity.Category;
import com.mashiro.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 14:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDetailVO {
    private Long id;
    private String title;//文章标题
    private String content;//文章正文
    private Boolean appreciation;//赞赏开关
    private Boolean commentEnabled;//评论开关
    private Boolean top;//是否置顶
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private Integer views;//浏览次数

    private Category category;//文章分类
    private List<Tag> tags = new ArrayList<>();//文章标签
}