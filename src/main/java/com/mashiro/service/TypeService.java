package com.mashiro.service;

import com.mashiro.pojo.Type;

import java.util.List;
/**
 * @Description: 文章分类业务层接口
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 8:25
 */
public interface TypeService {
    /**
    * @Description: 新增分类
    * @param type
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:26
    *
    */
    int createType(Type type);

    /**
    * @Description: 根据id获取分类
    * @param id
    * @return {@link Type}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:27
    *
    */
    Type getTypeById(Long id);

    /**
    * @Description: 根据Name获取分类
    * @param name
    * @return {@link Type}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:28
    *
    */
    Type getTypeByName(String name);

    /**
    * @Description: 获取所有分类
    * @param
    * @return {@link List< Type>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:29
    *
    */
    List<Type> getAllType();

    /**
    * @Description: 查询分类下的所有博客
    * @param
    * @return {@link List< Type>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:29
    *
    */
    List<Type> getAllTypeAndBlog();

    /**
     * @Description: 限制主页上显示的分类数量
     * @param
     * @return {@link List< Type>}
     * @throws
     * @author BeforeOne
     * @data 2021/4/25 8:29
     *
     */
    List<Type> getFiveTypeAndBlog();

    /**
    * @Description: 根据id修改分类
    * @param type
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:30
    *
    */
    int updateType(Type type);

    /**
    * @Description: 根据id删除分类
    * @param id
    * @return {@link int}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:30
    *
    */
    int deleteType(Long id);
}
