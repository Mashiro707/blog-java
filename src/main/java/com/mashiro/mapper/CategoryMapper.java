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
    * @param type
    * @return {@link int}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/25 8:19
    *
    */
    int createType(Category type);
    
    /**
    * @Description: 通过id获取分类
    * @param id
    * @return {@link Category}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/25 8:20
    *
    */
    Category getTypeById(@Param("id") Long id);
    
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
    * @Description: 获取所有分类
    * @param 
    * @return {@link List<   Category  >}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/25 8:20
    *
    */
    List<Category> getAllType();

    /**
    * @Description: 查询分类下的所有博客
    * @param
    * @return {@link List<  Category  >}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:22
    *
    */
    List<Category> getAllTypeAndBlog();

    /**
    * @Description: 根据id修改分类信息
    * @param type
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:22
    *
    */
    int updateType(Category type);

    /**
    * @Description: 根据id删除分类
    * @param id
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:23
    *
    */
    int deleteType(@Param("id") Long id);
}
