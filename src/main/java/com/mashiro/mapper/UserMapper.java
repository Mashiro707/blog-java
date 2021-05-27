package com.mashiro.mapper;

import com.mashiro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @Description: 用户持久层接口
 * @Author: BeforeOne
 * @Date: Created in 2021/4/23 15:56
 *
 */

@Mapper
@Repository
public interface UserMapper {

    /**
    * @Description: 根据用户名查询用户(用于登录验证)
    * @param username
    * @return {@link User}
    * @throws
    * @author BeforeOne
    * @data 2021/4/23 16:39
    *
    */
    User findUserByUsername(String username);

}
