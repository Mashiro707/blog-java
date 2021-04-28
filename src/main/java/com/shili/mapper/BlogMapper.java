package com.shili.mapper;

import com.shili.pojo.Blog;
import com.shili.vo.BlogAndTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 博客持久层接口
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 20:36
 */
@Mapper
@Repository
public interface BlogMapper {

    /**
    * @Description: 新增博客
    * @param blog
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 20:39
    *
    */
    int createBlog(Blog blog);

    /**
    * @Description: 博客与标签关联存储
    * @param blogAndTag
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/26 10:43
    *
    */
    int createBlogAndTag(BlogAndTag blogAndTag);

    /**
    * @Description: 删除博客和标签关联
    * @param id
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/26 11:05
    *
    */
    int deleteBlogAndTag(Long id);

    /**
    * @Description: 删除博客
    * @param id
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 20:40
    *
    */
    int deleteBlog(Long id);

    /**
    * @Description: 编辑博客
    * @param blog
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 20:40
    *
    */
    int updateBlog(Blog blog);

    /**
    * @Description: 根据Id查询博客，用于新增博客
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
    * @return {@link List<Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 21:14
    *
    */
    List<Blog> getAllBlog();
    
    /**
    * @Description: 根据类型id获取博客
    * @param typeId
    * @return {@link List< Blog>}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/26 8:25
    *
    */
    List<Blog> getBlogByTypeId(Long typeId);
    
    /**
    * @Description: 根据标签id获取博客
    * @param tagId
    * @return {@link List< Blog>}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/26 8:26
    *
    */
    List<Blog> getBlogByTagId(Long tagId);

    /**
    * @Description: 后台根据标题、分类、推荐搜索博客
    * @param blog
    * @return {@link List< Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/26 8:27
    *
    */
    List<Blog> searchAllBlog(Blog blog);

    /**
    * @Description: 主页博客展示
    * @param
    * @return {@link List< Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 13:00
    *
    */
    List<Blog> getIndexBlog();

    /**
    * @Description: 首页推荐博客展示
    * @param
    * @return {@link List<Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 13:17
    *
    */
    List<Blog> getAllRecommendBlog();

    /**
    * @Description: 首页全局搜索
    * @param query
    * @return {@link List<Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 13:51
    *
    */
    List<Blog> getSearchBlog(String query);
    
    /**
    * @Description: 博客详情
    * @param id
    * @return {@link Blog}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/28 14:11
    *
    */
    Blog getDetailedBlog(@Param("id") Long id);

    /**
    * @Description: 更新浏览次数
    * @param blog
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 14:18
    *
    */
    int updateViews(Blog blog);
    
    /**
    *
    * @Description: 查询所有年份
    * @param 
    * @return {@link List< String>}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/28 16:39
    *
    */
    List<String> findGroupYear();
    
    /**
    *
    * @Description: 按年份查询博
    * @param year
    * @return {@link List< Blog>}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/28 16:40
    *
    */
    List<Blog> findByYear(@Param("year") String year);



}
