package com.mashiro.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashiro.common.Result;
import com.mashiro.entity.ExceptionLog;
import com.mashiro.service.ExceptionLogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 14:31
 */
@RestController
@RequestMapping("/admin")
public class ExceptionLogAdminController {

    private final ExceptionLogService exceptionLogService;

    public ExceptionLogAdminController(ExceptionLogService exceptionLogService) {
        this.exceptionLogService = exceptionLogService;
    }

    /**
     * 分页查询异常日志列表
     *
     * @param date     按操作时间查询
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return {@link Result}
     * @author Mashiro
     * @date 2021/5/30 14:34
     */
    @GetMapping("/exceptionLogs")
    public Result exceptionLogs(@RequestParam(defaultValue = "") String[] date,
                                @RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize) {
        //初始化时间
        String startDate = null;
        String endDate = null;
        //从前端拿时间
        if (date.length == 2) {
            startDate = date[0];
            endDate = date[1];
        }
        String orderBy = "create_time desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<ExceptionLog> pageInfo = new PageInfo<>(exceptionLogService.getExceptionLogListByDate(startDate, endDate));
        return Result.success( pageInfo);
    }

    /**
     * 按id删除异常日志
     *
     * @param id 日志id
     * @return {@link Result}
     * @author Mashiro
     * @date 2021/5/30 14:39
     */
    @DeleteMapping("/exceptionLog")
    public Result delete(@RequestParam Long id) {
        exceptionLogService.deleteExceptionLogById(id);
        return Result.success("删除成功");
    }

}
