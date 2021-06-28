package com.mashiro.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mashiro.annotation.OperationLogger;
import com.mashiro.common.Result;
import com.mashiro.entity.Blog;
import com.mashiro.entity.Comment;
import com.mashiro.service.BlogService;
import com.mashiro.service.CommentService;
import com.mashiro.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/29 21:58
 */
@Api(tags = "后台评论管理模块")
@RestController
@RequestMapping("/admin")
public class CommentAdminController {

    private final CommentService commentService;
    private final BlogService blogService;

    public CommentAdminController(CommentService commentService, BlogService blogService) {
        this.commentService = commentService;
        this.blogService = blogService;
    }

    /**
    * 删除评论
    * @param id 评论 id
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 22:12
    */
    @ApiOperation(value = "删除评论")
    @ApiImplicitParam(name = "id", value = "博客Id", required = true, dataType = "Long", paramType = "query")
    @OperationLogger("删除评论")
    @DeleteMapping("/comment")
    public Result deleteComment(@RequestParam Long id){
        commentService.deleteCommentById(id);
        return Result.success("删除评论成功");
    }

    /**
    * 修改评论
    * @param comment
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 22:22
    */
    @ApiOperation(value = "修改评论")
    @OperationLogger("修改评论")
    @PutMapping("/comment")
    public Result updateComment(@RequestBody Comment comment) {
        if (StringUtils.isEmpty(comment.getNickname(), comment.getAvatar(), comment.getEmail(), comment.getIp(), comment.getContent())) {
            return Result.error("参数有误");
        }
        commentService.updateComment(comment);
        return Result.success("评论修改成功");
    }

    /**
    * 更新评论邮件提醒状态
    * @param id 评论 id
    * @param notice 评论邮件提醒状态
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 22:14
    */
    @ApiOperation(value = "更新评论邮件提醒状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "博客Id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "notice", value = "是否邮件提醒", required = true, dataType = "Boolean", paramType = "query")
    })
    @OperationLogger("更新评论邮件提醒状态")
    @PutMapping("/comment/notice")
    public Result updateNotice(@RequestParam Long id, @RequestParam Boolean notice){
        commentService.updateCommentNoticeById(id, notice);
        return Result.success("修改成功");
    }

    /**
    * 更新评论可见状态
    * @param id
    * @param published
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 22:19
    */
    @ApiOperation(value = "更新评论可见状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "博客Id", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "published", value = "是否公开", required = true, dataType = "Boolean", paramType = "query")
    })
    @OperationLogger("更新评论可见状态")
    @PutMapping("/comment/published")
    public Result updatePublished(@RequestParam Long id, @RequestParam Boolean published){
        commentService.updateCommentPublishedById(id, published);
        return Result.success("修改成功");
    }

    /**
    * 获取所有博客id和title 供评论分类的选择
    * @param
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 22:24
    */
    @ApiOperation(value = "获取所有博客id和title 供评论分类的选择")
    @GetMapping("/blogIdAndTitle")
    public Result blogIdAndTitle() {
        List<Blog> blogs = blogService.getBlogIdAndTitleList();
        return Result.success(blogs);
    }

    /**
    * 按页面和博客id查询评论list
    * @param page 评论的页面
    * @param blogId 博客 id
    * @param pageNum 页码
    * @param pageSize 每页个数
    * @return {@link Result}
    * @author Mashiro
    * @date 2021/5/29 22:24
    */
    @ApiOperation(value = "评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "所属页面（1.博客 2. 关于我（暂未开放））", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "blogId", value = "博客id", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, defaultValue = "1", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数目", required = true, defaultValue = "10", dataType = "Integer", paramType = "query")
    })
    @GetMapping("/comments")
    public Result comments(@RequestParam(defaultValue = "") Integer page,
                           @RequestParam(defaultValue = "") Long blogId,
                           @RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {
        String orderBy = "create_time desc";
        PageHelper.startPage(pageNum, pageSize, orderBy);
        PageInfo<Comment> pageInfo = new PageInfo<>(commentService.getListByPageAndParentCommentId(page, blogId, (long) -1));
        return Result.success(pageInfo);
    }

}
