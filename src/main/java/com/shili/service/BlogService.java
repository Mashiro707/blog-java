package com.shili.service;

import com.shili.pojo.Blog;
import com.shili.vo.BlogAndTag;

import java.util.List;
import java.util.Map;

/**
 * @Description: 博客业务层接口
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 20:56
 */

public interface BlogService {

    /**
    * @Description: 新增博客
    * @param blog
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 20:57
    *
    */
    int createBlog(Blog blog);

    /**
    * @Description: 删除博客
    * @param id
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 20:57
    *
    */
    int deleteBlog(Long id);

    /**
    * @Description:
    * @param blog
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 20:40
    *
    */
    int updateBlog(Blog blog);

    /**
    * @Description: 根据Id查询博客，用户新增博客
    * @param id
    * @return {@link Blog}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 21:05
    *
    */
    Blog getBlogById(Long id);

    /**
     * @Description: 查询所有博客，用于后台展示。
     * @param
     * @return {@link List < Blog>}
     * @throws
     * @author BeforeOne
     * @data 2021/4/25 21:14
     *
     */
    List<Blog> getAllBlog();

    /**
    * @Description: 后台根据条件（标题、分类、是否推荐）查询
    * @param blog
    * @return {@link List< Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/26 8:42
    *
    */
    List<Blog> searchAllBlog(Blog blog);

    /**
     * @Description: 主页博客展示
     * @param
     * @return {@link List< Blog>}
     * @throws
     * @author BeforeOne
     * @data 2021/4/28 13:03
     *
     */
    List<Blog> getIndexBlog();

    /**
     * @Description: 首页推荐博客展示
     * @param
     * @return {@link List<Blog>}
     * @throws
     * @author BeforeOne
     * @data 2021/4/28 13:18
     *
     */
    List<Blog> getAllRecommendBlog();

    /**
     * @Description: 首页全局搜索
     * @param query
     * @return {@link List<Blog>}
     * @throws
     * @author BeforeOne
     * @data 2021/4/28 13:57
     *
     */
    List<Blog> getSearchBlog(String query);

    /**
     * @Description: 博客详情
     * @param id
     * @return {@link Blog}
     * @throws
     * @author BeforeOne
     * @data 2021/4/28 14:15
     *
     */
    Blog getDetailedBlog(Long id);

    /**
    * @Description: 右侧最新博客
    * @param
    * @return {@link List<Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 15:30
    *
    */
    List<Blog> getPortRecommendBlog();

    /**
    * @Description: 底部最新的三篇推荐博客
    * @param
    * @return {@link List<Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 15:26
    *
    */
    List<Blog> getThreeRecommendBlog();

    /**
    * @Description: 根据分类Id获取博客列表
    * @param typeId
    * @return {@link List< Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 15:52
    *
    */
    List<Blog> getBlogByTypeId(Long typeId);

    /**
    * @Description: 根据标签Id获取博客列表
    * @param tagId
    * @return {@link List<Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 16:11
    *
    */
    List<Blog> getBlogByTagId(Long tagId);

    /**
    * @Description: 归档展示，使用Map容器。key=年份，value=博客列表
    * @param
    * @return {@link Map<String, List<Blog>>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 16:37
    *
    */
    Map<String, List<Blog>> archiveBlog();

    /**
    * @Description: 查询博客条数
    * @param
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 16:38
    *
    */
    int countBlog();
}
