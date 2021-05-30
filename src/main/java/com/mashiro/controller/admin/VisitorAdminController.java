package com.mashiro.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashiro.common.Result;
import com.mashiro.entity.Visitor;
import com.mashiro.service.VisitorService;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 16:05
 */
@RestController
@RequestMapping("/admin")
public class VisitorAdminController {

    private final VisitorService visitorService;

    public VisitorAdminController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    /**
     * 分页查询访客列表
     *
     * @param date     按最后访问时间查询
     * @param pageNum  页码
     * @param pageSize 每页个数
     * @return
     */
    @GetMapping("/visitors")
    public Result visitors(@RequestParam(defaultValue = "") String[] date,
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
        PageInfo<Visitor> pageInfo = new PageInfo<>(visitorService.getVisitorListByDate(startDate, endDate));
        return Result.success(pageInfo);
    }

    /**
     * 按id删除访客
     * 按uuid删除Redis缓存
     *
     * @param id   访客id
     * @param uuid 访客uuid
     * @return
     */
    @DeleteMapping("/visitor")
    public Result delete(@RequestParam Long id, @RequestParam String uuid) {
        visitorService.deleteVisitor(id, uuid);
        return Result.success("删除成功");
    }
}
