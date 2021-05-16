package com.mashiro.service;

import com.mashiro.pojo.Tag;

import java.util.List;

/**
 * @Description: 标签业务层接口
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 11:09
 */
public interface TagService {

    /**
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
     * @Description: 根据id获取标签
     * @param id
     * @return {@link Tag}
     * @throws
     * @author BeforeOne
     * @data 2021/4/25 10:57
     *
     */
    Tag getTagById(Long id);

    /**
     * @Description: 根据name获取标签
     * @param name
     * @return {@link Tag}
     * @throws
     * @author BeforeOne
     * @data 2021/4/25 11:01
     *
     */
    Tag getTagByName(String name);

    /**
     * @Description: 获取全部标签
     * @param
     * @return {@link List < Tag>}
     * @throws
     * @author BeforeOne
     * @data 2021/4/25 11:02
     *
     */
    List<Tag> getAllTag();

    /**
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
    int deleteTag(Long id);

    /**
    * @Description: 从字符串中获取tag集合
    * @param text
    * @return {@link List< Tag>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/26 9:19
    *
    */
    List<Tag> getTagByString(String text);

}
