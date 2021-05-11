package com.shili.service.serviceImpl;

import com.shili.mapper.TypeMapper;
import com.shili.pojo.Type;
import com.shili.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
/**
 * @Description: 文章分类业务实现层
 * @Author: BeforeOne
 * @Date: Created in 2021/4/25 8:31
 */
@Service
public class TypeServiceImpl implements TypeService {

    /*注入持久层接口*/
    @Autowired
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public int createType(Type type) {
        return typeMapper.createType(type);
    }

    @Transactional
    @Override
    public Type getTypeById(Long id) {
        return typeMapper.getTypeById(id);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeMapper.getTypeByName(name);
    }

    @Override
    public List<Type> getAllType() {
        return typeMapper.getAllType();
    }

    @Transactional
    @Override
    public List<Type> getAllTypeAndBlog() {
        return typeMapper.getAllTypeAndBlog();
    }

    @Transactional
    @Override
    public List<Type> getFiveTypeAndBlog() {
        List<Type> tmpType = new ArrayList<>();
        List<Type> allType = typeMapper.getAllTypeAndBlog();
        for (int i = 0; i < 5; i++) {
            tmpType.add(allType.get(i));
        }
        return tmpType;
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    @Transactional
    @Override
    public int deleteType(Long id) {
        return typeMapper.deleteType(id);
    }
}
