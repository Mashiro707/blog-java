package com.shili.service.serviceImpl;

import com.shili.NotFoundException;
import com.shili.mapper.BlogMapper;
import com.shili.pojo.Blog;
import com.shili.pojo.Tag;
import com.shili.service.BlogService;
import com.shili.util.MarkdownUtils;
import com.shili.vo.BlogAndTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public List<Blog> getIndexBlog() {
        return blogMapper.getIndexBlog();
    }

    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogMapper.getAllRecommendBlog();
    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogMapper.getSearchBlog(query);
    }

    @Override
    public Blog getDetailedBlog(Long id) {
        System.out.println("blogId:"+ id);
        Blog blog = blogMapper.getDetailedBlog(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));  //将Markdown格式转换成html
        blog.setViews(blog.getViews()+1);
        blogMapper.updateViews(blog);
        return blog;
    }

    @Override
    public List<Blog> getPortRecommendBlog() {
        List<Blog> tmpRecommendBlog = new ArrayList();
        List<Blog> allRecommendBlog = blogMapper.getAllRecommendBlog();
        for(int i = 0; i < 7; i++)
            tmpRecommendBlog.add(allRecommendBlog.get(i));
        return tmpRecommendBlog;
    }

    @Override
    public List<Blog> getThreeRecommendBlog() {
        List<Blog> tmpRecommendBlog = new ArrayList();
        List<Blog> allRecommendBlog = blogMapper.getAllRecommendBlog();
        if(allRecommendBlog.size() > 3) {
            for (int i = 0; i < 3; i++)
                tmpRecommendBlog.add(allRecommendBlog.get(i));
            return tmpRecommendBlog;
        }
        else
            return allRecommendBlog;
    }

    @Override
    public List<Blog> getBlogByTypeId(Long typeId) {
        return blogMapper.getBlogByTypeId(typeId);
    }

    @Override
    public List<Blog> getBlogByTagId(Long tagId) {
        return blogMapper.getBlogByTagId(tagId);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.findGroupYear();
        Set<String> set = new HashSet<>(years);  //set去掉重复的年份
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : set)
            map.put(year, blogMapper.findByYear(year));
        return map;
    }

    @Override
    public int countBlog() {
        return blogMapper.getAllBlog().size();
    }
}
