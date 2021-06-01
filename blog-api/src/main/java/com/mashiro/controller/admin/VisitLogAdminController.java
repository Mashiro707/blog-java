package com.mashiro.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashiro.common.Result;
import com.mashiro.entity.VisitLog;
import com.mashiro.service.VisitLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 15:23
 */
@RestController
@RequestMapping("/admin")
public class VisitLogAdminController {

    private final VisitLogService visitLogService;

    public VisitLogAdminController(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }

    /**
     * 分页查询访问日志列表
     *
     * @param uuid     按访客标识码模糊查询
     * @param date     按访问时间查询
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return {@link Result}
     * @author Mashiro
     * @date 2021/5/30 15:26
     */
    @GetMapping("/visitLogs")
    public Result visitLogs(@RequestParam(defaultValue = "") String uuid,
                            @RequestParam(defaultValue = "") String[] date,
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
        PageInfo<VisitLog> pageInfo = new PageInfo<>(visitLogService.getVisitLogListByUUIDAndDate(StringUtils.trim(uuid), startDate, endDate));
        return Result.success(pageInfo);
    }

    /**
     * 按id删除访问日志
     *
     * @param id 日志id
     * @return {@link Result}
     * @author Mashiro
     * @date 2021/5/30 15:26
     */
    @DeleteMapping("/visitLog")
    public Result delete(@RequestParam Long id) {
        visitLogService.deleteVisitLogById(id);
        return Result.success("删除成功");
    }

}
