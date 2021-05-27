package com.mashiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 16:21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfoDTO {
    private String username;
    private String password;
}
