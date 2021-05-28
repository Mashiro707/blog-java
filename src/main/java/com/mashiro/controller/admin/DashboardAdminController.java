package com.mashiro.controller.admin;

import com.mashiro.common.Result;
import com.mashiro.service.DashboardService;
import com.mashiro.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Description: 后台管理仪表盘控制层
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 20:03
 */
@RestController
@RequestMapping("/admin")
public class DashboardAdminController {
    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private RedisService redisService;

    /**
    * 仪表盘显示数据
    *
    * @param
    * @return {@link Result}
    *
    * @author Mashiro
    * @data 2021/5/27 22:28
    *
    */
    @GetMapping("/dashboard")
    public Result dashboard(){
        //今日点击量
        int todayPV = dashboardService.countVisitLogByToday();
        //今日用户访问量
        int todayUV ;
        //博客总数量
        int blogCount = dashboardService.getBlogCount();
        //评论总数量
        int commentCount = dashboardService.getCommentCount();
        //分类下文章数量
        Map<String, List> categoryBlogCountMap = dashboardService.getCategoryBlogCountMap();

        return Result.success();
    }
}
