package com.mashiro.controller.admin;

import com.mashiro.dto.LoginInfo;
import com.mashiro.pojo.User;
import com.mashiro.service.UserService;
import com.mashiro.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
    * @Description: 登录
    * @param loginInfo
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/5/15 16:48
    *
    */
    @PostMapping("/login")
    public Result login(@RequestBody LoginInfo loginInfo) {

        User user = userService.checkUser(loginInfo.getUsername(), loginInfo.getPassword());
        if (!"ROLE_admin".equals(user.getRole())) {
            return Result.create(403, "无权限");
        }

    }

    /**
    * @Description: 登出
    * @param session
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/5/15 16:48
    *
    */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
