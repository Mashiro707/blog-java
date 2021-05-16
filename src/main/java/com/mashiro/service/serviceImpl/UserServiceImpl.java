package com.mashiro.service.serviceImpl;

import com.mashiro.mapper.UserMapper;
import com.mashiro.pojo.User;
import com.mashiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @Description: 用户业务接口实现类
 * @Author: BeforeOne
 * @Date: Created in 2021/4/23 16:52
 *
 */

@Service
public class UserServiceImpl implements UserService {
    //注入
    @Autowired
    private UserMapper userMapper;
    /**
    *
    * @Description: UserService接口中checkUser方法的实现
    * @param username
    * @param password
    * @return {@link User}
    * @throws
    * @author BeforeOne
    * @data 2021/4/23 16:52
    *
    */
    @Override
    public User checkUser(String username, String password) {
        User user = userMapper.queryByUsernameAndPassword(username, password);
        return user;
    }
}
