package com.mashiro.mapper;

import com.mashiro.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 文章分类持久层接口
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 8:07
 */

@Mapper
@Repository
public interface CategoryMapper {

    /**
    * @Description: 新增分类
    * @param category  分类对象
    * @return {@link int}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/25 8:19
    *
    */
    int saveCategory(Category category);
    
    /**
    * @Description: 通过id获取分类
    * @param id
    * @return {@link Category}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/25 8:20
    *
    */
    Category getCategoryById(@Param("id") Long id);
    
    /**
    * @Description: 通过Name获取分类
    * @param name
    * @return {@link Category}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/25 8:20
    *
    */
    Category getTypeByName(@Param("name") String name);
    
    /**
    * @Description: 获取所有分类，生成List
    * @param 
    * @return {@link List<Category>}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/25 8:20
    *
    */
    List<Category> getCategoryList();

    /**
    * 获取所有分类List不查询id
    * @param
    * @return {@link List< Category>}
    * @author Mashiro
    * @data 2021/5/28 10:19
    *
    */
    List<Category> getCategoryNameList();

    /**
    * @Description: 查询分类下的所有博客
    * @param
    * @return {@link List<  Category  >}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:22
    *
    */
    List<Category> getCategoryBlogList();

    /**
    * @Description: 根据id修改分类信息
    * @param category
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:22
    *
    */
    int updateCategory(Category category);

    /**
    * @Description: 根据id删除分类
    * @param id
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:23
    *
    */
    int deleteCategoryById(@Param("id") Long id);

}
