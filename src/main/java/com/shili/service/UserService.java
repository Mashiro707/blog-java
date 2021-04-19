package com.shili.service;

import com.shili.pojo.User;

public interface UserService {
    User checkUser(String username, String password);
}
