package com.mashiro.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashiro.common.Result;
import com.mashiro.entity.OperationLog;
import com.mashiro.service.OperationLogService;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 14:50
 */
@RestController
@RequestMapping("/admin")
public class OperationLogServiceController {

    private final OperationLogService operationLogService;

    public OperationLogServiceController(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    /**
     * 分页查询操作日志列表
     *
     * @param date     按操作时间查询
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return {@link Result}
     * @author Mashiro
     * @date 2021/5/30 14:52
     */
    @GetMapping("/operationLogs")
    public Result operationLogs(@RequestParam(defaultValue = "") String[] date,
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
        PageInfo<OperationLog> pageInfo = new PageInfo<>(operationLogService.getOperationLogListByDate(startDate, endDate));
        return Result.success(pageInfo);
    }

    /**
     * 按id删除操作日志
     *
     * @param id 日志id
     * @return {@link Result}
     * @author Mashiro
     * @date 2021/5/30 14:52
     */
    @DeleteMapping("/operationLog")
    public Result delete(@RequestParam Long id) {
        operationLogService.deleteOperationLogById(id);
        return Result.success("删除成功");
    }
}