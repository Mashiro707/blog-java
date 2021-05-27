/*
package com.mashiro.controller;

import com.mashiro.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

*/
/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/4/28 16:34
 *//*


@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;

    */
/**
    * @Description: 归档页面展示
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/28 16:34
    *
    *//*

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogService.archiveBlog());
        model.addAttribute("blogCount", blogService.countBlog());
        return "archives";
    }
}
*/
