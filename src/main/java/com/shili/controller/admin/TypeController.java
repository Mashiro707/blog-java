package com.shili.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shili.pojo.Type;
import com.shili.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 文章分类控制管理器
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 8:40
 */
@Controller
@RequestMapping("/admin")
public class TypeController {
    /*注入业务层接口*/
    @Autowired
    private TypeService typeService;

    /**
    *
    * @Description: 跳转至分类新增页面
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:47
    *
    */
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    /**
    *
    * @Description: 新增分类
    * @param type
    * @param attributes
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 8:42
    *
    */
    @PostMapping("/types")
    public String post(@Valid Type type, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null){
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:admin/types/input";
        }
        int i = typeService.createType(type);
        if (i == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }
    /**
    *
    * @Description: 分页查询分类列表
    * @param model
    * @param pageNum
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 9:16
    *
    */
    @GetMapping("/types")
    public String list(Model model,@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum){
        /*根据id，倒序*/
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, 3, orderBy);
        List<Type> list = typeService.getAllType();
        /*得到分页结果对象*/
        PageInfo<Type> pageInfo = new PageInfo<Type>(list);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    /**
    *
    * @Description: 跳转至编辑分类页面
    * @param id
    * @param model
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 9:25
    *
    */
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type", typeService.getTypeById(id));
        return "admin/types-input";
    }

    /**
    *
    * @Description: 编辑修改分类
    * @param type
    * @param attributes
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 9:28
    *
    */
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null){
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:admin/types/input";
        }
        int i = typeService.updateType(type);
        if (i == 0){
            attributes.addFlashAttribute("message", "编辑失败");
        }else {
            attributes.addFlashAttribute("message", "编辑成功");
        }
        return "redirect:/admin/types";
    }

    /**
    *
    * @Description: 删除分类
    * @param id
    * @param attributes
    * @return {@link String}
    * @throws
    * @author BeforeOne
    * @data 2021/4/25 9:35
    *
    */
    @GetMapping("/types/{id}/delete")
        public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
