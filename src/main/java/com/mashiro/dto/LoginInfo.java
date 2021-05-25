package com.mashiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 登录信息
 * @Author: BeforeOne
 * @Date: Created in 2021/5/24 23:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    private String username;
    private String password;
}
