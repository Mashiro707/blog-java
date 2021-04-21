package com.shili.service;

import com.shili.pojo.Blog;
import com.shili.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(String query, Pageable pageable);

    Blog saveBlog(Blog blog);

    List<Blog> listRecommendBlogTop(Integer size);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);
}
