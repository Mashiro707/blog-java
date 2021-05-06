package com.shili.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 关于我界面管理控制器
 * @Author: BeforeOne
 * @Date: Created in 2021/4/28 16:45
 */
@Controller
public class AboutShowController {
    /**
    * @Description: 跳转至关于我界面
    * @param
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 16:45
    *
    */
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
