package com.shili.service.serviceImpl;


import com.shili.mapper.TagMapper;
import com.shili.pojo.Tag;
import com.shili.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional
    @Override
    public int createTag(Tag tag) {
        return tagMapper.createTag(tag);
    }

    @Transactional
    @Override
    public Tag getTagById(Long id) {
        return tagMapper.getTagById(id);
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        return tagMapper.getTagByName(name);
    }

    @Transactional
    @Override
    public List<Tag> getAllTag() {
        return tagMapper.getAllTag();
    }

    @Transactional
    @Override
    public List<Tag> getAllTagAndBlog() {
        return tagMapper.getAllTagAndBlog();
    }

    @Transactional
    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Transactional
    @Override
    public int deleteTag(Long id) {
        return tagMapper.deleteTag(id);
    }

    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long aLong : longs) {
            tags.add(tagMapper.getTagById(aLong));
        }
        return tags;
    }

    /**
    * @Description: 把前端的tagIds字符串转换为list集合
    * @param ids
    * @return {@link List<Long>}
    * @throws
    * @author BeforeOne
    * @data 2021/4/26 9:22
    *
    */
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

}
