package com.shili.mapper;

import com.shili.pojo.User;
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
    *
    * @Description: 根据用户名和密码查询用户(用于登录验证)
    * @param username
    * @param password
    * @return {@link User}
    * @throws
    * @author BeforeOne
    * @data 2021/4/23 16:39
    *
    */
    User queryByUsernameAndPassword(@Param("username") String username,
                                   @Param("password") String password);

}
