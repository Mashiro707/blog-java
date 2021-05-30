package com.mashiro.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashiro.annotation.OperationLogger;
import com.mashiro.common.Result;
import com.mashiro.entity.Tag;
import com.mashiro.service.BlogService;
import com.mashiro.service.TagService;
import com.mashiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/29 18:45
 */
@RestController
@RequestMapping("/admin")
public class TagAdminController {

    private final TagService tagService;
    private final BlogService blogService;

    public TagAdminController(TagService tagService, BlogService blogService) {
        this.tagService = tagService;
        this.blogService = blogService;
    }

    /**
    * 添加标签
    * @param tag 标签对象
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 18:59
    */
    @OperationLogger("添加标签")
    @PostMapping("/tag")
    public Result saveTag(@RequestBody Tag tag){
        return saveAndUpdateCheckDate(tag, "save");
    }

    /**
    * 删除标签
    * @param id 标签id
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 18:59
    */
    @OperationLogger("删除标签")
    @DeleteMapping("/tag")
    public Result deleteTagByBlogId(@RequestParam Long id){
        //删除存在博客关联的分类后，该博客的查询会出异常
        int num = blogService.countBlogByTagId(id);
        if (num != 0) {
            return Result.error("已有博客与此标签关联，不可删除");
        }
        tagService.deleteTagById(id);
        return Result.success("删除评论成功");
    }

    /**
    * 修改标签
    * @param tag 标签对象
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 19:00
    */
    @OperationLogger("修改标签")
    @PutMapping("/tag")
    public Result updateTag(@RequestBody Tag tag){
        return saveAndUpdateCheckDate(tag, "update");
    }

    /**
    * 标签列表
    * @param pageNum
    * @param pageSize
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 19:02
    */
    @GetMapping("/tags")
    public Result tagList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        String orderBy = "id desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<Tag> pageInfo = new PageInfo<>(tagService.getTagList());
        return Result.success(pageInfo);
    }

    private Result saveAndUpdateCheckDate(Tag tag, String type) {
        if (StringUtils.isEmpty(tag.getName())) {
            return Result.error("参数不能为空");
        }
        //查询标签是否已存在
        Tag tag1 = tagService.getTagByName(tag.getName());
        //如果 tag1.getId().equals(tag.getId()) == true 就是更新标签
        if (tag1 != null && !tag1.getId().equals(tag.getId())) {
            return Result.error("该标签已存在");
        }
        if ("save".equals(type)) {
            tagService.saveTag(tag);
            return Result.success("添加标签成功");
        } else {
            tagService.updateTag(tag);
            return Result.success("标签更新成功");
        }
    }
}
