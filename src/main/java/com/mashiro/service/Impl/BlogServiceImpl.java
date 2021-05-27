/*
package com.mashiro.service.Impl;

import com.mashiro.exception.NotFoundException;
import com.mashiro.mapper.BlogMapper;
import com.mashiro.mapper.CommentMapper;
import com.mashiro.entity.Blog;
import com.mashiro.entity.Comment;
import com.mashiro.entity.Tag;
import com.mashiro.service.BlogService;
import com.mashiro.service.TagService;
import com.mashiro.util.MarkdownUtils;
import com.mashiro.vo.BlogAndTagVO;
import com.mashiro.vo.WebInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

*/
/**
 * @Description: 博客业务实现类
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 20:59
 *//*


@Service
public class BlogServiceImpl implements BlogService {

    */
/*注入持久层接口*//*

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private TagService tagService;

    @Override
    public int createBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blogMapper.saveBlog(blog);
        Long id = blog.getId();
        List<Tag> tags;
        tags = blog.getTags();
        BlogAndTagVO blogAndTagVO = null;
        for (Tag tag : tags) {
            //这里blog类中的指针相连，但是我们查tag是根据BlogAndTag查的，所以应该连接这个
            blogAndTagVO = new BlogAndTagVO(tag.getId(), id);
            blogMapper.saveBlogAndTag(blog.getId(),tag.getId());
        }
        return 1;
    }

    @Override
    public int deleteBlog(Long id) {
        List<Comment> replyComments = commentMapper.findByBlogId(id);
        for (Comment replyComment : replyComments) {
            commentMapper.deleteComment(replyComment);
        }
        blogMapper.deleteBlogAndTag(id);
        return blogMapper.deleteBlog(id);
    }

    @Override
    public int deleteBlogAndTag(Long id) {
        return blogMapper.deleteBlogAndTag(id);
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTagVO blogAndTagVO = null;
        //这里应该先删，再更新
        blogMapper.deleteBlogAndTag(blog.getId());
        //防止null值
        for (int i = 0; i < tags.size(); i++) {
            Tag tag = tags.get(i);
            if (tag == null)
                continue;
            blogAndTagVO = new BlogAndTagVO(tag.getId(), blog.getId());
            blogMapper.createBlogAndTag(blogAndTagVO);
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
        List<Blog> blogList = blogMapper.getIndexBlog();
        List<Blog> blogs = setTags(blogList);
        return blogs;
    }


    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogMapper.getAllRecommendBlog();
    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        List<Blog> blogList = blogMapper.getSearchBlog(query);
        List<Blog> blogs = setTags(blogList);
        return blogs;
    }

    @Override
    public Blog getDetailedBlog(Long id) {
        Blog blog = blogMapper.getDetailedBlog(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));  //将Markdown格式转换成html
        blog.setViews(blog.getViews() + 1);
        blogMapper.updateViews(blog);
        return blog;
    }

    @Override
    public List<Blog> getPortRecommendBlog() {
        List<Blog> tmpRecommendBlog = new ArrayList();
        List<Blog> allRecommendBlog = blogMapper.getAllRecommendBlog();
        for (int i = 0; i < 7; i++)
            tmpRecommendBlog.add(allRecommendBlog.get(i));
        return tmpRecommendBlog;
    }

    @Override
    public List<Blog> getThreeRecommendBlog() {
        List<Blog> tmpRecommendBlog = new ArrayList();
        List<Blog> allRecommendBlog = blogMapper.getAllRecommendBlog();
        if (allRecommendBlog.size() > 3) {
            for (int i = 0; i < 3; i++)
                tmpRecommendBlog.add(allRecommendBlog.get(i));
            return tmpRecommendBlog;
        } else
            return allRecommendBlog;
    }

    @Override
    public List<Blog> getBlogByTypeId(Long typeId) {
        List<Blog> blogList = blogMapper.getBlogByTypeId(typeId);
        List<Blog> blogs = setTags(blogList);
        return blogs;
    }

    @Override
    public List<Blog> getBlogByTagId(Long tagId) {
        List<Blog> blogList = blogMapper.getBlogByTagId(tagId);
        List<Blog> blogs = setTags(blogList);
        return blogs;
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

    @Override
    public WebInfoVo getWebInfo() {
        Integer views = blogMapper.getViews();
        Integer commentCount = commentMapper.getCommentCount();
        WebInfoVo webInfoVo = new WebInfoVo();
        webInfoVo.setViews(views);
        webInfoVo.setCommentCount(commentCount);
        return webInfoVo;
    }

    */
/**
    * @Description: 将tags_id字符串转换成 list
    * @param ids
    * @return {@link List< Long>}
    * @throws
    * @author BeforeOne
    * @data 2021/5/15 16:32
    *
    *//*

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    // 设置tags
    private List<Blog> setTags(List<Blog> blogList){
        for (Blog blog : blogList) {
            List<Tag> tags = new ArrayList<>();
            Long blogId = blog.getId();
            List<Long> tagIds =convertToList(blog.getTagIds());
            for (Long tagId : tagIds) {
                Tag tag = tagService.getTagById(tagId);
                tags.add(tag);
            }
            blog.setTags(tags);
        }
        return blogList;
    }


}
*/
