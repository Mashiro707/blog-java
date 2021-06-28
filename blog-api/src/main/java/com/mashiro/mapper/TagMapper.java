package com.mashiro.mapper;

import com.mashiro.entity.Tag;
import com.mashiro.vo.TagBlogCountVO;
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
    * @Description: 新增标签
    * @param tag
    * @return {@link Tag}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 10:56
    *
    */
    int saveTag(Tag tag);

    /**
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
    * @Description: 获取全部标签list
    * @param
    * @return {@link List< Tag>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 11:02
    *
    */
    List<Tag> getTagList();

    /**
    * @Description: 查询标签下所有的博客
    * @param
    * @return {@link List< Tag>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 11:04
    *
    */
    List<Tag> getTagBlogList();

    /**
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
    * @Description: 删除标签
    * @param id
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 11:06
    *
    */
    int deleteTagById(@Param("id") Long id);

    /**
    * 查询每个标签的博客数量
    *
    * @param
    * @return {@link List< TagBlogCountVO>}
    *
    * @author Mashiro
    * @data 2021/5/28 9:58
    **/
    List<TagBlogCountVO> getTagBlogCount();

    /**
    * 获取所有标签List不查询id
    * @param
    * @return {@link List< Tag>}
    * @author Mashiro
    * @data 2021/5/28 10:07
    *
    */
    List<Tag> getTagListNotId();

    /**
    * 按博客id查询List
    * @param blogId
    * @return {@link List< Tag>}
    * @author Mashiro
    * @data 2021/5/28 10:06
    *
    */
    List<Tag> getTagListByBlogId(Long blogId);
}
