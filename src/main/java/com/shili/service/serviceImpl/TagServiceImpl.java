package com.shili.service.serviceImpl;


import com.shili.mapper.TagMapper;
import com.shili.pojo.Tag;
import com.shili.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 文章分类业务层实现类
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 11:08
 */

@Service
public class TagServiceImpl implements TagService {
    /*注入标签持久层接口*/
    @Autowired
    private TagMapper tagMapper;

    @Override
    public int createTag(Tag tag) {
        return tagMapper.createTag(tag);
    }

    @Override
    public Tag getTagById(Long id) {
        return tagMapper.getTagById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Override
    public List<Tag> getAllTag() {
        return tagMapper.getAllTag();
    }

    @Override
    public List<Tag> getAllTagAndBlog() {
        return tagMapper.getAllTagAndBlog();
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    public int deleteTag(Long id) {
        return tagMapper.deleteTag(id);
    }
}
