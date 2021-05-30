package com.mashiro.mapper;

import com.mashiro.entity.ScheduleJob;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 20:17
 */
@Repository
@Mapper
public interface ScheduleJobMapper {

    /**
    * 新增任务
    * @param scheduleJob
    * @return {@link int}
    * @author Mashiro
    * @date 2021/5/30 17:37
    */
    int saveJob(ScheduleJob scheduleJob);

    /**
    * 删除任务
    * @param jobId
    * @return {@link int}
    * @author Mashiro
    * @date 2021/5/30 17:42
    */
    int deleteJobById(Long jobId);

    /**
    * 更新任务
    * @param scheduleJob
    * @return {@link int}
    * @author Mashiro
    * @date 2021/5/30 17:46
    */
    int updateJob(ScheduleJob scheduleJob);

    /**
    * 更新任务状态
    * @param jobId
    * @param status
    * @return {@link int}
    * @author Mashiro
    * @date 2021/5/30 21:41
    */
    int updateJobStatusById(Long jobId, Boolean status);

    /**
    * 通过id获取任务信息
    * @param jobId
    * @return
    * @author Mashiro
    * @date 2021/5/30 21:44
    */
    ScheduleJob getJobById(Long jobId);

    /**
    * 获取任务列表
    * @param
    * @return
    * @author Mashiro
    * @date 2021/5/30 21:45
    */
    List<ScheduleJob> getJobList();



}
