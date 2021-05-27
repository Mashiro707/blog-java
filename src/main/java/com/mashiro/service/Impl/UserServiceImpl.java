package com.mashiro.service.Impl;

import com.mashiro.mapper.UserMapper;
import com.mashiro.entity.User;
import com.mashiro.service.UserService;
import com.mashiro.util.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @Description: 用户业务接口实现类
 * @Author: BeforeOne
 * @Date: Created in 2021/4/23 16:52
 *
 */

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User user = userMapper.findUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在");
        }else if (!HashUtils.matchBC(password, user.getPassword())){
            throw new UsernameNotFoundException("密码错误");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
}
