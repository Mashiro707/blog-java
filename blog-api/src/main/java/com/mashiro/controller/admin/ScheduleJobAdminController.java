package com.mashiro.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashiro.annotation.OperationLogger;
import com.mashiro.common.Result;
import com.mashiro.entity.ScheduleJob;
import com.mashiro.entity.ScheduleJobLog;
import com.mashiro.service.ScheduleJobService;
import com.mashiro.util.common.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 21:51
 */
@RestController
@RequestMapping("/admin")
public class ScheduleJobAdminController {

    private final ScheduleJobService scheduleJobService;

    public ScheduleJobAdminController(ScheduleJobService scheduleJobService) {
        this.scheduleJobService = scheduleJobService;
    }

    /**
    * 添加定时任务
    * @param scheduleJob 定时任务实体类
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/30 21:58
    */
    @OperationLogger("添加定时任务")
    @PostMapping("/job")
    public Result saveJob(@RequestBody ScheduleJob scheduleJob){
        scheduleJob.setStatus(false);
        scheduleJob.setCreateTime(new Date());
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.saveJob(scheduleJob);
        return Result.success("添加定时任务成功");
    }

    /**
    * 删除定时任务
    * @param jobId 定时任务id
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/30 22:01
    */
    @OperationLogger("删除定时任务")
    @DeleteMapping("/job")
    public Result deleteJob(@RequestParam Long jobId){
        scheduleJobService.deleteJobById(jobId);
        return Result.success("删除定时任务成功");
    }

    /**
    * 修改定时任务
    * @param scheduleJob 定时任务实体类
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/30 22:05
    */
    @OperationLogger("修改定时任务")
    @PutMapping("/job")
    public Result updateJob(@RequestBody ScheduleJob scheduleJob){
        scheduleJob.setStatus(false);
        ValidatorUtils.validateEntity(scheduleJob);
        scheduleJobService.updateJob(scheduleJob);
        return Result.success("修改定时任务成功");
    }
    /**
    * 修改定时任务状态
    * @param jobId 定时任务id
    * @param status 定时任务状态
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/30 22:07
    */
    @OperationLogger("修改定时任务状态")
    @PutMapping("/job/status")
    public Result updateJobStatus(@RequestParam Long jobId, @RequestParam Boolean status){
        scheduleJobService.updateJobStatusById(jobId, status);
        return Result.success("修改定时任务状态成功");
    }

    /**
    * 立即执行定时任务
    * @param jobId 定时任务id
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/30 22:15
    */
    @OperationLogger("立即执行定时任务")
    @PostMapping("/job/run")
    public Result runJob(@RequestParam Long jobId){
        scheduleJobService.runJobById(jobId);
        return Result.success("提交执行");
    }

    /**
    * 定时任务列表
    * @param pageNum 页码
    * @param pageSize 每页个数
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/30 22:17
    */
    @GetMapping("/jobs")
    public Result jobList(@RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<ScheduleJob> pageInfo = new PageInfo<>(scheduleJobService.getJobList());
        return Result.success(pageInfo);
    }

    /**
    * 定时任务日志列表
    * @param date 显示的时间区域
    * @param pageNum 页码
    * @param pageSize 每页个数
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/30 22:18
    */
    @GetMapping("/job/logs")
    public Result logs(@RequestParam(defaultValue = "") String[] date,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        String startDate = null;
        String endDate = null;
        if (date.length == 2) {
            startDate = date[0];
            endDate = date[1];
        }
        String orderBy = "create_time desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<ScheduleJobLog> pageInfo = new PageInfo<>(scheduleJobService.getJobLogListByDate(startDate, endDate));
        return Result.success(pageInfo);
    }

    /**
    * 删除定时任务日志
    * @param logId 定时任务日志id
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/30 22:19
    */
    @DeleteMapping("/job/log")
    public Result delete(@RequestParam Long logId) {
        scheduleJobService.deleteJobLogByLogId(logId);
        return Result.success("删除成功");
    }
}

