package com.shili.service.serviceImpl;

import com.shili.mapper.BlogMapper;
import com.shili.pojo.Blog;
import com.shili.pojo.Tag;
import com.shili.service.BlogService;
import com.shili.vo.BlogAndTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 博客业务实现类
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 20:59
 */

@Service
public class BlogServiceImpl implements BlogService {

    /*注入持久层接口*/
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public int createBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blogMapper.createBlog(blog);
        Long id = blog.getId();
        List<Tag> tags;
        tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            //这里blog类中的指针相连，但是我们查tag是根据BlogAndTag查的，所以应该连接这个
            blogAndTag = new BlogAndTag(tag.getId(), id);
            blogMapper.createBlogAndTag(blogAndTag);
        }
        return 1;
    }

    @Override
    public int deleteBlog(Long id) {
        return blogMapper.deleteBlog(id);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        //这里应该先删，再更新
        blogMapper.deleteBlogAndTag(blog.getId());
        //防止null值
        for (int i = 0; i < tags.size(); i++) {
            Tag tag = tags.get(i);
            if(tag == null)
                continue;
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogMapper.createBlogAndTag(blogAndTag);
        }
        return blogMapper.updateBlog(blog);
    }

    @Override
    public Blog getBlogById(Long id) {
        return blogMapper.getBlogById(id);
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogMapper.getAllBlog();
    }

    @Override
    public List<Blog> searchAllBlog(Blog blog) {
        return blogMapper.searchAllBlog(blog);
    }
}
