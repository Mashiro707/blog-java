package com.mashiro.mapper;

import com.mashiro.pojo.Type;
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
public interface TypeMapper {
    /**
    * @Description: 新增分类
    * @param type
    * @return {@link int}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/25 8:19
    *
    */
    int createType(Type type);
    
    /**
    * @Description: 通过id获取分类
    * @param id
    * @return {@link Type}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/25 8:20
    *
    */
    Type getTypeById(@Param("id") Long id);
    
    /**
    * @Description: 通过Name获取分类
    * @param name
    * @return {@link Type}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/25 8:20
    *
    */
    Type getTypeByName(@Param("name") String name);
    
    /**
    * @Description: 获取所有分类
    * @param 
    * @return {@link List< Type>}
    * @throws 
    * @author BeforeOne
    * @data 2021/4/25 8:20
    *
    */
    List<Type> getAllType();

    /**
    * @Description: 查询分类下的所有博客
    * @param
    * @return {@link List<Type>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:22
    *
    */
    List<Type> getAllTypeAndBlog();

    /**
    * @Description: 根据id修改分类信息
    * @param type
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:22
    *
    */
    int updateType(Type type);

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
