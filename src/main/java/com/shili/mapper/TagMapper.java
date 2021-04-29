package com.shili.mapper;

import com.shili.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 标签持久层接口
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 10:55
 */

@Mapper
@Repository
public interface TagMapper {

    /**
    *
    * @Description: 新增标签
    * @param tag
    * @return {@link Tag}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 10:56
    *
    */
    int createTag(Tag tag);

    /**
    *
    * @Description: 根据id获取标签
    * @param id
    * @return {@link Tag}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 10:57
    *
    */
    Tag getTagById(@Param("id") Long id);

    /**
    *
    * @Description: 根据name获取标签
    * @param name
    * @return {@link Tag}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 11:01
    *
    */
    Tag getTagByName(@Param("name") String name);

    /**
    *
    * @Description: 获取全部标签
    * @param
    * @return {@link List< Tag>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 11:02
    *
    */
    List<Tag> getAllTag();

    /**
    *
    * @Description: 查询标签下所有的博客
    * @param
    * @return {@link List< Tag>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 11:04
    *
    */
    List<Tag> getAllTagAndBlog();

    /**
    *
    * @Description: 更新修改标签
    * @param tag
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 11:06
    *
    */
    int updateTag(Tag tag);

    /**
    *
    * @Description: 删除标签
    * @param id
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 11:06
    *
    */
    int deleteTag(@Param("id") Long id);
}
