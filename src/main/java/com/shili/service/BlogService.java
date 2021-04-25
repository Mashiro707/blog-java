package com.shili.service;

import com.shili.pojo.Blog;

import java.util.List;

public interface BlogService {
    /*Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);
    *//**
    *
    * @param query
    * @param pageable
    * @return {@link Page< Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/23 16:33
    *
    *//*
    Page<Blog> listBlog(String query, Pageable pageable);
    *//**
    *
    * @param blog
    * @return {@link Blog}
    * @throws
    * @author BeforeOne
    * @data 2021/4/23 16:32
    *
    *//*
    Blog saveBlog(Blog blog);
    *//**
    *
    * @param size
    * @return {@link List< Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/23 16:32
    *
    *//*
    List<Blog> listRecommendBlogTop(Integer size);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);*/
}
