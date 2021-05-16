package com.mashiro.controller.admin;

import com.mashiro.pojo.User;
import com.mashiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
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
    * @param username
    * @param password
    * @param session
    * @param attributes
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/5/15 16:48
    *
    */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名或密码错误");
            return "redirect:/admin";
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
