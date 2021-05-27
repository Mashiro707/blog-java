package com.mashiro.service.Impl;

import com.mashiro.mapper.CategoryMapper;
import com.mashiro.entity.Category;
import com.mashiro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * @Description: 文章分类业务实现层
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 8:31
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Transactional
    @Override
    public int createType(Category type) {
        return categoryMapper.createType(type);
    }

    @Transactional
    @Override
    public Category getTypeById(Long id) {
        return categoryMapper.getTypeById(id);
    }

    @Transactional
    @Override
    public Category getTypeByName(String name) {
        return categoryMapper.getTypeByName(name);
    }

    @Override
    public List<Category> getAllType() {
        return categoryMapper.getAllType();
    }

    @Transactional
    @Override
    public List<Category> getAllTypeAndBlog() {
        return categoryMapper.getAllTypeAndBlog();
    }

    @Transactional
    @Override
    public List<Category> getFiveTypeAndBlog() {
        List<Category> tmpType = new ArrayList<>();
        List<Category> allType = categoryMapper.getAllTypeAndBlog();
        if (allType.size()>5){
            for (int i = 0; i < 5; i++) {
                tmpType.add(allType.get(i));
            }
            return tmpType;
        }
        return allType;
    }

    @Transactional
    @Override
    public int updateType(Category type) {
        return categoryMapper.updateType(type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        return categoryMapper.deleteType(id);
    }
}
