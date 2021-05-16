package com.mashiro.service;

import com.mashiro.pojo.User;

public interface UserService {
    User checkUser(String username, String password);
}
