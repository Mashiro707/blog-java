package com.mashiro.controller.admin;

import com.mashiro.common.RedisKey;
import com.mashiro.common.Result;
import com.mashiro.entity.CityVisitor;
import com.mashiro.service.DashboardService;
import com.mashiro.service.RedisService;
import com.mashiro.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    private RedisUtils redisUtils;

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
        System.out.println("进入了Controller 仪表盘");
        //今日点击量
        int todayPV = dashboardService.countVisitLogByToday();
        //今日用户访问量
        int todayUV = redisUtils.countBySet(RedisKey.IDENTIFICATION_SET);
        //博客总数量
        int blogCount = dashboardService.getBlogCount();
        //评论总数量
        int commentCount = dashboardService.getCommentCount();
        //各分类下文章数量
        Map<String, List> categoryBlogCountMap = dashboardService.getCategoryBlogCountMap();
        //各标签下评论的数量
        Map<String, List> tagBlogCountMap = dashboardService.getTagBlogCountMap();
        //访客记录
        Map<String, List> visitorRecordMap = dashboardService.getVisitRecordMap();
        //访客城市信息
        List<CityVisitor> cityVisitorList = dashboardService.getCityVisitorList();
        //所有信息放入map
        Map<String, Object> map = new HashMap<>();
        map.put("pv",todayPV);
        map.put("uv",todayUV);
        map.put("blogCount",blogCount);
        map.put("commentCount",commentCount);
        map.put("category",categoryBlogCountMap);
        map.put("tag",tagBlogCountMap);
        map.put("visitorRecord",visitorRecordMap);
        map.put("cityVisitor",cityVisitorList);

        return Result.success(map);
    }
}
