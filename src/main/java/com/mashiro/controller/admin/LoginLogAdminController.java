package com.mashiro.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashiro.common.Result;
import com.mashiro.entity.LoginLog;
import com.mashiro.service.LoginLogService;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 14:54
 */
@RestController
@RequestMapping("/admin")
public class LoginLogAdminController {

    private final LoginLogService loginLogService;

    public LoginLogAdminController(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    /**
     * 分页查询登录日志列表
     *
     * @param date     按操作时间查询
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return {@link Result}
     * @author Mashiro
     * @date 2021/5/30 15:00
     */
    @GetMapping("/loginLogs")
    public Result loginLogs(@RequestParam(defaultValue = "") String[] date,
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
        PageInfo<LoginLog> pageInfo = new PageInfo<>(loginLogService.getLoginLogByDate(startDate, endDate));
        return Result.success(pageInfo);
    }

    /**
     * 按id删除登录日志
     *
     * @param id 日志id
     * @return {@link Result}
     * @author Mashiro
     * @date 2021/5/30 15:00
     */
    @DeleteMapping("/loginLog")
    public Result delete(@RequestParam Long id) {
        loginLogService.deleteLoginLog(id);
        return Result.success("删除成功");
    }
}
