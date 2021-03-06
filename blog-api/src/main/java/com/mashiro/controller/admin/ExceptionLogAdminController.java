package com.mashiro.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashiro.annotation.OperationLogger;
import com.mashiro.common.Result;
import com.mashiro.entity.ExceptionLog;
import com.mashiro.service.ExceptionLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 后台日志管理模块
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 14:31
 */
@Api(tags = "后台异常日志管理模块")
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
    @ApiOperation(value = "分页查询异常日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", value = "操作时间", dataType = "String[]", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, defaultValue = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数目", required = true, defaultValue = "10", dataType = "Integer", paramType = "query")

    })
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
    @ApiOperation(value = "删除异常日志")
    @ApiImplicitParam(name = "id", value = "异常日志Id", required = true, dataType = "Long", paramType = "query")
    @OperationLogger(value = "删除异常日志")
    @DeleteMapping("/exceptionLog")
    public Result delete(@RequestParam Long id) {
        exceptionLogService.deleteExceptionLogById(id);
        return Result.success("删除成功");
    }

}
