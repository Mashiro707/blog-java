package com.shili.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shili.pojo.Tag;
import com.shili.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


/**
 * @Description: 博客标签控制管理器
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 11:30
 */

@Controller
@RequestMapping("/admin")
public class TagController {
    /*注入标签业务层接口*/
    @Autowired
    private TagService tagService;

    /**
    *
    * @Description: 分页展示所有标签
    * @param model
    * @param pageNum
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 14:30
    *
    */
    @GetMapping("/tags")
    public String tags(Model model, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, 3 ,orderBy);
        List<Tag> tags = tagService.getAllTag();
        PageInfo<Tag> pageInfo = new PageInfo<Tag>(tags);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    /**
    *
    * @Description: 跳转至新增标签页面
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 14:35
    *
    */
    @GetMapping("/tags/input")
    public String toInputTag(Model model){
        /*返回一个tag对象给前端th:object*/
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    /**
    *
    * @Description: 跳转至编辑标签页面
    * @param id
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 14:37
    *
    */
    @GetMapping("/tags/{id}/input")
    public String toEditTag(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.getTagById(id));
        return "admin/tags-input";
    }

    /**
    *
    * @Description: 新增标签
    * @param tag
    * @param attributes
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 14:41
    *
    */
    @PostMapping("/tags")
    public String inputTag(Tag tag, RedirectAttributes attributes){
        Tag t = tagService.getTagByName(tag.getName());
        if (t != null){
            attributes.addFlashAttribute("msg", "不能添加重复的标签");
            return "redirect:/admin/tags/input";
        }
        int i = tagService.createTag(tag);
        if (i == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        /*不能直接跳转到tags页面，否则不会显示tag数据(没经过tags方法)*/
        return "redirect:/admin/tags";
    }

    /**
    *
    * @Description:
    * @param id
    * @param tag
    * @param attributes
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 14:46
    *
    */
    @PostMapping("/tags/{id}")
    public String editTag(@PathVariable Long id, Tag tag, RedirectAttributes attributes){
        Tag t = tagService.getTagByName(tag.getName());
        if (t != null){
            attributes.addFlashAttribute("msg", "不能添加重复的标签");
            return "redirect:/admin/tags/input";
        }
        int i = tagService.updateTag(tag);
        if (i == 0){
            attributes.addFlashAttribute("message", "更新失败");
        }else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        /*不能直接跳转到tags页面，否则不会显示tag数据(没经过tags方法)*/
        return "redirect:/admin/tags";
    }

    /**
    *
    * @Description:
    * @param id
    * @param attributes
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 14:53
    *
    */
    @GetMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/admin/tags";
    }
}
