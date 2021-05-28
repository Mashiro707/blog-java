package com.mashiro.service.Impl;

import com.mashiro.entity.Category;
import com.mashiro.entity.CityVisitor;
import com.mashiro.mapper.BlogMapper;
import com.mashiro.mapper.CommentMapper;
import com.mashiro.mapper.VisitLogMapper;
import com.mashiro.mapper.VisitorMapper;
import com.mashiro.service.CategoryService;
import com.mashiro.service.DashboardService;
import com.mashiro.service.TagService;
import com.mashiro.vo.CategoryBlogCountVO;
import com.mashiro.vo.TagBlogCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private VisitLogMapper visitLogMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;


    @Override
    public int getBlogCount() {
        return blogMapper.countBlog();
    }

    @Override
    public int getCommentCount() {
        return commentMapper.countComment();
    }

    @Override
    public int countVisitLogByToday() {
        return visitLogMapper.countVisitLogByToday();
    }

    @Override
    public Map<String, List> getCategoryBlogCountMap() {
        //获取每个分类Id下文章的数目，此时name为null
        List<CategoryBlogCountVO> categoryBlogCountVOList = blogMapper.getCategoryBlogCountList();
        //获取所有分类的名称和Id
        List<Category> categoryList = categoryService.getCategoryList();
        //所有分类名称
        List<String> sector = new ArrayList<>();
        for (Category category : categoryList) {
            sector.add(category.getName());
        }
        //分类名称对应博客数目
        List<CategoryBlogCountVO> scallop = new ArrayList<>();
        //如果每个分类下都有对应的博客
        if (categoryBlogCountVOList.size() == categoryList.size()){
            Map<Long, String> m = new HashMap<>();
            //将所有分类的Id和名称以 key-value 方式存储
            for (Category category : categoryList) {
                m.put(category.getId(), category.getName());
            }
            //因为每个分类下都有博客，所以可以通过 m 给 categoryBlogCountVOList 的 name 都赋上name，存入scallop中
            for (CategoryBlogCountVO categoryBlogCountVO : categoryBlogCountVOList) {
                categoryBlogCountVO.setName(m.get(categoryBlogCountVO.getId()));
                    scallop.add(categoryBlogCountVO);
            }
        }else {
            Map<Long, Integer> m = new HashMap<>();
            //将含有博客的分类的id和value 以 key-value 方式存储
            for (CategoryBlogCountVO categoryBlogCountVO : categoryBlogCountVOList) {
                m.put(categoryBlogCountVO.getId(), categoryBlogCountVO.getValue());
            }
            //遍历所有分类，新建一个CategoryBlogCountVO对象，给每个对象设置name和value
            //value的值从 m 中取出，如果 m 中没有对应id的分类，则count为空，即将这个分类的博客数目设置为0
            //最后每个对象都放入 scallop
            for (Category category : categoryList) {
                CategoryBlogCountVO categoryBlogCountVO = new CategoryBlogCountVO();
                categoryBlogCountVO.setName(category.getName());
                Integer count = m.get(category.getId());
                if (count == null){
                    categoryBlogCountVO.setValue(0);
                }else {
                    categoryBlogCountVO.setValue(count);
                }
                scallop.add(categoryBlogCountVO);
            }
        }
        Map<String, List> map = new HashMap<>();
        map.put("sector", sector);
        map.put("scallop", scallop);
        return map;
    }

    @Override
    public Map<String, List> getTagBlogCountMap() {
        List<TagBlogCountVO> tagBlogCountVOList = tagService.
        return null;
    }

    @Override
    public Map<String, List> getVisitRecordMap() {
        return null;
    }

    @Override
    public List<CityVisitor> getCityVisitorList() {
        return null;
    }

}
