package com.mashiro.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashiro.annotation.OperationLogger;
import com.mashiro.common.Result;
import com.mashiro.dto.BlogDTO;
import com.mashiro.dto.BlogVisibilityDTO;
import com.mashiro.entity.Blog;
import com.mashiro.entity.Category;
import com.mashiro.entity.Tag;
import com.mashiro.entity.User;
import com.mashiro.service.BlogService;
import com.mashiro.service.CategoryService;
import com.mashiro.service.CommentService;
import com.mashiro.service.TagService;
import com.mashiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/28 16:49
 */
@RestController
@RequestMapping("/admin")
public class BlogAdminController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private CommentService commentService;

    /**
    * 保存草稿或者新增博客
    * @param blogDTO 博客DTO
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 10:18
    */
    @OperationLogger("发布博客")
    @PostMapping("/blog")
    public Result savaBlog(@RequestBody BlogDTO blogDTO){
        return saveAndUpdateCheckData(blogDTO, "save");
    }
    /**
    * 删除博客，同时删除该博客下的所有评论，维护blog_tag表
    * @param id 博客id
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 10:23
    */
    @OperationLogger("删除博客")
    @DeleteMapping("/blog")
    public Result deleteBlog(@RequestParam Long id){
        System.out.println("进入了BlogAdminController----deleteBlog");
        commentService.deleteCommentsByBlogId(id);
        blogService.deleteBlogAndTagByBlogId(id);
        blogService.deleteBlogById(id);
        return Result.success("删除成功");
    }

    /**
    * 更新博客
    * @param blogDTO 博客DTO
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 10:28
    */
    @OperationLogger("更新博客")
    @PutMapping("/blog")
    public Result updateBlog(@RequestBody BlogDTO blogDTO){
        System.out.println("进入了BlogAdminController----updateBlog");
        return saveAndUpdateCheckData(blogDTO, "update");
    }

    /**
    * 更新博客置顶状态
    * @param id 博客id
    * @param top 是否置顶
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 10:33
    */
    @OperationLogger("更新博客置顶状态")
    @PutMapping("/blog/top")
    public Result updateTop(@RequestParam Long id, @RequestParam Boolean top){
        System.out.println("进入了BlogAdminController----updateTop");
        if(blogService.updateBlogTopById(id, top) != 1){
            return Result.error();
        }
        return Result.success();
    }

    /**
    * 更新博客推荐状态
    * @param id 博客id
    * @param recommend 是否推荐
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 10:36
    */
    @OperationLogger("更新博客推荐状态")
    @PutMapping("/blog/recommend")
    public Result updateRecommend(@RequestParam Long id, @RequestParam Boolean recommend){
        System.out.println("进入了BlogAdminController----updateRecommend");
        if (blogService.updateBlogRecommendById(id, recommend) != 1){
            return Result.error();
        }
        return Result.success();
    }
    /**
    * 更新博客可见性状态
    * @param id
    * @param blogVisibilityDTO
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 10:39
    */
    @OperationLogger("更新博客可见性状态")
    @PutMapping("/blog/{id}/visibility")
    public Result updateVisibility(@PathVariable Long id, @RequestBody BlogVisibilityDTO blogVisibilityDTO){
        System.out.println("进入了BlogAdminController----updateVisibility");
        blogService.updateBlogVisibilityById(id, blogVisibilityDTO);
        return Result.success();
    }

    /**
    * 获取分页列表和标签列表
    * @param
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 10:40
    */
    @GetMapping("/categoryAndTag")
    public Result categoryAndTag() {
        System.out.println("进入了BlogAdminController----categoryAndTag");
        List<Category> categories = categoryService.getCategoryList();
        List<Tag> tags = tagService.getTagList();
        Map<String, Object> map = new HashMap<>();
        map.put("categories", categories);
        map.put("tags", tags);
        return Result.success(map);
    }
    /**
    * 获取博客文章列表
    * @param title 按标题模糊查询
    * @param categoryId 按分类id查询
    * @param pageNum 页码
    * @param pageSize 每页个数
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 9:40
    */
    @GetMapping("/blogs")
    public Result blogs(@RequestParam(defaultValue = "") String title,
                        @RequestParam(defaultValue = "") Integer categoryId,
                        @RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "10") Integer pageSize){
        System.out.println("进入了BlogAdminController----blogs");
        String order = "create_time desc";
        PageHelper.startPage(pageNum, pageSize, order);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogService.getBlogListByTitleAndCategoryId(title, categoryId));
        List<Category> categoryList = categoryService.getCategoryList();
        Map<String, Object> map = new HashMap<>();
        map.put("blogs", pageInfo);
        map.put("categories", categoryList);
        return Result.success(map);
    }

    /**
    * 根据博客id获取博客
    * @param id 博客id
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 10:42
    */
    @GetMapping("/blog")
    public Result getBlog(@RequestParam Long id){
        System.out.println("进入了BlogAdminController----getBlog");
        return Result.success(blogService.getBlogById(id));
    }

    /**
    * 执行博客添加或更新操作：校验参数是否合法，添加分类、标签，维护博客标签关联表
    * @param blogDTO 博客DTO
    * @param type 新增或者更新
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 9:47
        */
    private Result saveAndUpdateCheckData(BlogDTO blogDTO, String type){
        System.out.println("进入了BlogAdminController----saveAndUpdateCheckData");
        //必填字段不能为空
        if (StringUtils.isEmpty(blogDTO.getTitle(), blogDTO.getContent(), blogDTO.getDescription(), blogDTO.getFirstPicture())){
            return Result.error("参数错误");
        }
        //处理分类
        Object cate = blogDTO.getCate();
        //不能为空
        if (cate == null){
            return Result.error("分类不能为空");
        }
        //如果是选择已经存在的分类，前端传入的为 int 类型
        if (cate instanceof Integer){
            Category category = categoryService.getCategoryById(((Integer) cate).longValue());
            blogDTO.setCategory(category);
            //如果前端传入的是字符串类型，那么说明为新增操作
        }else if (cate instanceof String){
            //判断是否存在
            Category category = categoryService.getCategoryByName((String) cate);
            if (category != null){
                Result.error("重复添加");
            }
            Category c = new Category();
            c.setName((String) cate);
            categoryService.saveCategory(c);
            blogDTO.setCategory(c);
        }else {
            Result.error();
        }
        //处理标签
        List<Object> tagList = blogDTO.getTagList();
        List<Tag> tags =new ArrayList<>();
        if (tagList == null){
            Result.error("标签不能为空");
        }
        //List中每个对象都需要处理
        for (Object t : tagList) {
            if (t instanceof Integer){
                Tag tag = tagService.getTagById(((Integer) t).longValue());
                tags.add(tag);
            } else if (t instanceof String) {//添加新标签
                //查询标签是否已存在
                Tag tag1 = tagService.getTagByName((String) t);
                if (tag1 != null) {
                    return Result.error("重复添加");
                }
                Tag tag = new Tag();
                tag.setName((String) t);
                tagService.saveTag(tag);
                tags.add(tag);
            } else {
                return Result.error();
            }
        }

        if (blogDTO.getViews() == null || blogDTO.getViews() < 0) {
            blogDTO.setViews(0);
        }
        //初始化时间
        Date date = new Date();
        if ("save".equals(type)) {
            blogDTO.setCreateTime(date);
            blogDTO.setUpdateTime(date);
            User user = new User();
            user.setId((long) 1);//个人博客默认只有一个作者
            blogDTO.setUser(user);

            blogService.saveBlog(blogDTO);
            //关联博客和标签(维护 blog_tag 表)
            for (Tag t : tags) {
                blogService.saveBlogAndTag(blogDTO.getId(), t.getId());
            }
            return Result.success("新增成功");
        } else {
            blogDTO.setUpdateTime(date);
            blogService.updateBlog(blogDTO);
            //关联博客和标签(维护 blog_tag 表)
            blogService.deleteBlogAndTagByBlogId(blogDTO.getId());
            for (Tag t : tags) {
                blogService.saveBlogAndTag(blogDTO.getId(), t.getId());
            }
            return Result.success("更新成功");
        }
    }
}
