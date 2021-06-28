package com.mashiro.controller;

import com.mashiro.common.Result;
import com.mashiro.dto.LoginInfoDTO;
import com.mashiro.entity.User;
import com.mashiro.service.SiteSettingService;
import com.mashiro.service.UserService;
import com.mashiro.util.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 前台登录
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 13:04
 */
@Api(tags = "登录模块")
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private SiteSettingService siteSettingService;

    /**
    * @Description: 前台登录，携带 admin token
    * @param loginInfoDTO
    * @return {@link Result}
    * @throws
    * @author BeforeOne
    * @data 2021/5/27 16:33
    *
    */
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginInfoDTO loginInfoDTO){
        User user = userService.findUserByUsernameAndPassword(loginInfoDTO.getUsername(), loginInfoDTO.getPassword());
        if (!"ROLE_admin".equals(user.getRole())){
            return Result.forbidde();
        }
        user.setPassword(null);
        String jwt = JwtUtils.generateToken("admin:" + user.getUsername());
        Map<String,Object> map =new HashMap<>();
        map.put("user", user);
        map.put("token", jwt);
        return Result.success(map);
    }

    @ApiOperation(value = "查询网页后缀信息")
    @GetMapping("/webTitleSuffix")
    public Result getWebTitleSuffix() {
        String webTitleSuffix = siteSettingService.getWebTitleSuffix();
        Map<String, Object> map = new HashMap<>();
        map.put("webTitleSuffix", webTitleSuffix);
        return Result.success(map);
    }

}
