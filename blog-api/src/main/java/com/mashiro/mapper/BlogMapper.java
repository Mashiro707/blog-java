package com.mashiro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashiro.dto.BlogDTO;
import com.mashiro.dto.BlogViewDTO;
import com.mashiro.dto.BlogVisibilityDTO;
import com.mashiro.entity.Blog;
import com.mashiro.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 博客持久层接口
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 20:36
 */
@Mapper
public interface BlogMapper extends BaseMapper {

    /**
    * @Description: 新增博客
    * @param blogDTO
     * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 20:39
    *
    */
    int saveBlog(BlogDTO blogDTO);

    /**
    * @Description: 维护博客和标签表
    * @param tagId
    * @param blogId
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 13:30
    *
    */
    int saveBlogAndTag(Long tagId, Long blogId);

    /**
    * @Description: 删除博客和标签关联
    * @param id
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/26 11:05
    *
    */
    int deleteBlogAndTagByBlogId(Long id);

    /**
    * @Description: 删除博客
    * @param id
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 20:40
    *
    */
    int deleteBlogById(Long id);

    /**
    * @Description: 编辑博客
    * @param blogDTO
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 20:40
    *
    */
    int updateBlog(BlogDTO blogDTO);

    /**
    * @Description: 更新博客推荐状态
    * @param blogId
    * @param recommend
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:18
    *
    */
    int updateBlogRecommendById(Long blogId, Boolean recommend);

    /**
    * @Description: 更新博客置顶状态
    * @param blogId
    * @param top
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:17
    *
    */
    int updateBlogTopById(Long blogId, Boolean top);

    /**
    * @Description: 更新博客可见性状态
    * @param blogId
    * @param bv
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:18
    *
    */
    int updateBlogVisibilityById(Long blogId, BlogVisibilityDTO bv);

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
    * @Description: 根据类型名称获取博客List
    * @param categoryName
    * @return {@link List< Blog>}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/26 8:25
    *
    */
    List<BlogInfoVO> getBlogInfoListByCategoryNameAndIsPublished(String categoryName);
    
    /**
    * @Description: 根据标签名称获取博客列表
    * @param tagName
    * @return {@link List< Blog>}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/26 8:26
    *
    */
    List<BlogInfoVO> getBlogInfoListByTagNameAndIsPublished(String tagName);

    /**
    * @Description: 按标题和分类查询博客List
    * @param title
    * @param categoryId
    * @return {@link List< Blog>}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 14:02
    *
    */
    List<Blog> getBlogListByTitleAndCategoryId(String title, Integer categoryId);

    /**
    * @Description: 按关键字根据文章内容搜索公开且无密码保护的博客文章
    * @param query
    * @return {@link List<SearchBlogVO>}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 14:06
    *
    */
    List<SearchBlogVO> getSearchBlogListByQueryAndIsPublished(String query);

    /**
    * @Description: 查询最新的公开博客
    * @param
    * @return {@link List< NewBlogVO>}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 14:27
    *
    */
    List<NewBlogVO> getNewBlogListByIsPublished();
    
    /**
    * @Description: 查询所有博客id和title
    * @param
    * @return {@link List< Blog>}
    * @throws 
    * @author BeforeOne
    * @data 2021/5/27 14:10
    *
    */
    List<Blog> getBlogIdAndTitleList();

    /**
    * @Description: 查询公开博客的简要信息
    * @param
    * @return {@link List< BlogInfoVO>}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:15
    *
    */
    List<BlogInfoVO> getBlogInfoListByIsPublished();

    /**
    * @Description: 按id查询公开博客
    * @param id
    * @return {@link BlogDetailVO}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:14
    *
    */
    BlogDetailVO getBlogByIdAndIsPublished(Long id);

    /**
    * @Description: 查询公开博客年月List
    * @param
    * @return {@link List< String>}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:15
    *
    */
    List<String> getGroupYearMonthByIsPublished();

    /**
    * @Description: 按年份查询博客
    * @param yearMonth
    * @return {@link List< Blog>}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/28 16:40
    *
    */
    List<ArchiveBlogVO> getArchiveBlogListByYearMonthAndIsPublished(String yearMonth);

    /**
    * @Description: 查询随机的公开且推荐文章
    * @param limitNum
    * @return {@link List< RandomBlogVO>}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:17
    *
    */
    List<RandomBlogVO> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend(Integer limitNum);

    /**
    * @Description: 查询所有文章的浏览量
    * @param
    * @return {@link List< BlogViewDTO>}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:17
    *
    */
    List<BlogViewDTO> getBlogViewsList();

    String getTitleByBlogId(Long id);

    List<ArchiveInfoVO>getTitleAndCreateTime();
    /**
    * @Description: 更新博客阅读次数
    * @param blogId
    * @param views
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:29
    *
    */
    int updateViews(Long blogId, Integer views);

    /**
    * @Description: 获取博客总访问量
    * @param
    * @return {@link Integer}
    * @throws
    * @author BeforeOne
    * @data 2021/5/12 14:47
    *
    */
    int getViews();

    /**
    * @Description: 查询博客总数
    * @param
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 13:58
    *
    */
    int countBlog();

    /**
    * @Description: 查询公开博客总数
    * @param
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:29
    *
    */
    int countBlogByIsPublished();

    /**
    * @Description: 按分类id查询博客数量
    * @param categoryId
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:29
    *
    */
    int countBlogByCategoryId(Long categoryId);

    /**
    * @Description: 按标签id查询博客数量
    * @param tagId
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:29
    *
    */
    int countBlogByTagId(Long tagId);

    /**
    * @Description: 查询博客是否启用评论
    * @param blogId
    * @return {@link Boolean}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:28
    *
    */
    Boolean getCommentEnabledByBlogId(Long blogId);

    /**
    * @Description: 查询博客是否公开
    * @param blogId
    * @return {@link Boolean}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:28
    *
    */
    Boolean getPublishedByBlogId(Long blogId);

    /**
    * @Description: 查询每个分类的博客数量
    * @param
    * @return {@link List< CategoryBlogCountVO>}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 15:28
    *
    */
    List<CategoryBlogCountVO> getCategoryBlogCountList();


}
