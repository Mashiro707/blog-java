package com.mashiro.service.Impl;

import com.mashiro.mapper.BlogMapper;
import com.mashiro.mapper.CommentMapper;
import com.mashiro.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 20:04
 */
@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int getBlogCount() {
        return blogMapper.countBlog();
    }

    @Override
    public int getCommentCount() {
        return commentMapper.countComment();
    }

}
