package com.mashiro.mapper;

import com.mashiro.entity.About;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/27 21:42
 */
@Mapper
@Repository
public interface AboutMapper {

    /**
    * 更新关于我设置
    * @param nameEn 名字
    * @param value 值
    * @return {@link int}
    * @author Mashiro
    * @date 2021/5/30 14:08
    */
    int updateAbout(String nameEn, String value);

    /**
    * 查询关于我设置
    * @param
    * @return {@link List< About>}
    * @author Mashiro
    * @date 2021/5/30 14:08
    */
    List<About> getList();

    /**
    * 查询关于我页面评论开关状态
    * @param
    * @return {@link String}
    * @author Mashiro
    * @date 2021/5/30 14:09
    */
    String getAboutCommentEnabled();
}
