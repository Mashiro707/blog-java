package com.mashiro.controller.admin;

import com.mashiro.annotation.OperationLogger;
import com.mashiro.common.Result;
import com.mashiro.entity.SiteSetting;
import com.mashiro.service.SiteSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 10:53
 */
@RestController
@RequestMapping("/admin")
public class SiteSettingAdminController {

    private final SiteSettingService siteSettingService;

    public SiteSettingAdminController(SiteSettingService siteSettingService) {
        this.siteSettingService = siteSettingService;
    }

    /**
    * 获取所有站点配置信息
    * @param
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/30 10:56
    */
    @GetMapping("/siteSettings")
    public Result siteSettings() {
        Map<String, List<SiteSetting>> typeMap = siteSettingService.getList();
        return Result.success(typeMap);
    }

    /**
     * 修改、删除(部分配置可为空，但不可删除)、添加(只能添加部分)站点配置
     *
     * @param map 包含所有站点信息更新后的数据 map => {settings=[更新后的所有配置List], deleteIds=[要删除的配置id List]}
     * @return
     */
    @OperationLogger("更新站点配置信息")
    @PostMapping("/siteSettings")
    public Result updateAll(@RequestBody Map<String, Object> map) {
        List<LinkedHashMap> siteSettings = (List<LinkedHashMap>) map.get("settings");
        List<Integer> deleteIds = (List<Integer>) map.get("deleteIds");
        siteSettingService.updateSiteSetting(siteSettings, deleteIds);
        return Result.success("更新成功");
    }

    /**
    * 查询网页后缀信息
    * @param
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/30 10:56
    */
    @GetMapping("/webTitleSuffix")
    public Result getWebTitleSuffix() {
        return Result.success(siteSettingService.getWebTitleSuffix());
    }

}
