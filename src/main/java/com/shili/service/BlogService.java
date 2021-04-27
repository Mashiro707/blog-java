package com.shili.service;

import com.shili.pojo.Blog;
import com.shili.vo.BlogAndTag;

import java.util.List;

/**
 * @Description: 博客业务层接口
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 20:56
 */

public interface BlogService {

    /**
    *
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
    *
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
    *
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
    *
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
     *
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
    *
    * @Description: 后台根据条件（标题、分类、是否推荐）查询
    * @param blog
    * @return {@link List< Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/26 8:42
    *
    */
    List<Blog> searchAllBlog(Blog blog);
}
