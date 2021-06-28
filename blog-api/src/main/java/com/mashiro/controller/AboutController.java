package com.mashiro.controller;

import com.mashiro.annotation.VisitLogger;
import com.mashiro.common.Result;
import com.mashiro.service.AboutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/6/3 19:23
 */
@Api(tags = "关于我模块")
@RestController
public class AboutController {
    @Autowired
    private AboutService aboutService;

    @ApiOperation(value = "关于我界面")
    @VisitLogger(behavior = "访问页面", content = "关于我")
    @GetMapping("/about")
    public Result about() {
        return Result.success(aboutService.getAboutInfo());
    }
}
