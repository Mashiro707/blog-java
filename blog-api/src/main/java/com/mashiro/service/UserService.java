package com.mashiro.service;

import com.mashiro.entity.User;

public interface UserService {

    User findUserByUsernameAndPassword(String username, String password);

    User getUserInfo(Long id);

}
