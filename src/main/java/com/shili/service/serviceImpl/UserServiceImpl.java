package com.shili.service.serviceImpl;

import com.shili.mapper.UserMapper;
import com.shili.pojo.User;
import com.shili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

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
        User user = userMapper.queryByUsernameAndPassword(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        return user;
    }
}
