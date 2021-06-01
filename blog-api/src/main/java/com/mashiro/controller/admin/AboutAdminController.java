package com.mashiro.controller.admin;

import com.mashiro.annotation.OperationLogger;
import com.mashiro.common.Result;
import com.mashiro.service.AboutService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 14:18
 */
@RestController
@RequestMapping("/admin")
public class AboutAdminController {

    private final AboutService aboutService;

    public AboutAdminController(AboutService aboutService) {
        this.aboutService = aboutService;
    }

    /**
    * 获取关于我页面配置
    * @param
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/30 14:19
    */
    @GetMapping("/about")
    public Result about() {
        return Result.success(aboutService.getAboutSetting());
    }

    /**
    * 修改关于我页面
    * @param map
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/30 14:19
    */
    @OperationLogger("修改关于我页面")
    @PutMapping("/about")
    public Result updateAbout(@RequestBody Map<String, String> map) {
        aboutService.updateAbout(map);
        return Result.success("修改成功");
    }

}
