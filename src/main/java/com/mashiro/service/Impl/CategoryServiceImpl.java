package com.mashiro.service.Impl;

import com.mashiro.common.RedisKey;
import com.mashiro.exception.NotFoundException;
import com.mashiro.mapper.CategoryMapper;
import com.mashiro.entity.Category;
import com.mashiro.service.CategoryService;
import com.mashiro.service.TagService;
import com.mashiro.util.RedisUtils;
import org.apache.ibatis.exceptions.PersistenceException;
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
    @Autowired
    private TagService tagService;
    @Autowired
    private RedisUtils redisUtils;

    @Transactional
    @Override
    public int saveCategory(Category category) {
        if (categoryMapper.saveCategory(category) != 1){
            throw new PersistenceException("新增分类失败");
        }
        redisUtils.deleteCacheByKey(RedisKey.CATEGORY_NAME_LIST);
        return 1;
    }

    @Override
    public Category getCategoryById(Long id) {
        Category category = categoryMapper.getCategoryById(id);
        if (category == null) {
            throw new NotFoundException("分类不存在");
        }
        return category;
    }

    @Override
    public Category getTypeByName(String name) {
        return categoryMapper.getTypeByName(name);
    }

    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    @Override
    public List<Category> getCategoryNameList() {
        //先从redis缓存中获取，获取不到再去数据库中查询
        String redisKey = RedisKey.CATEGORY_NAME_LIST;
        List<Category> categoryListFromRedis = redisUtils.getListByValue(redisKey);
        if (categoryListFromRedis != null){
            return categoryListFromRedis;
        }
        List<Category> categoryList = categoryMapper.getCategoryNameList();
        redisUtils.saveListToValue(redisKey, categoryList);
        return categoryList;
    }

    @Override
    public int updateCategory(Category category) {
        if (categoryMapper.updateCategory(category) != 1){
            throw new PersistenceException("更新失败");
        }
        redisUtils.deleteCacheByKey(RedisKey.CATEGORY_NAME_LIST);
        return 1;
    }

    @Override
    public int deleteCategoryById(Long id) {
        if (categoryMapper.deleteCategoryById(id) != 1){
            throw new PersistenceException("删除失败");
        }
        redisUtils.deleteByHashKey(RedisKey.CATEGORY_NAME_LIST);
        return 1;
    }
}
