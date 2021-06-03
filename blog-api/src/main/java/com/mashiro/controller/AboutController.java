package com.mashiro.controller;

import com.mashiro.annotation.VisitLogger;
import com.mashiro.common.Result;
import com.mashiro.entity.About;
import com.mashiro.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/6/3 19:23
 */
@RestController
public class AboutController {
    @Autowired
    private AboutService aboutService;

    @VisitLogger(behavior = "访问页面", content = "关于我")
    @GetMapping("/about")
    public Result about() {
        return Result.success(aboutService.getAboutInfo());
    }
}
