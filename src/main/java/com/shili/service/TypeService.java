package com.shili.service;

import com.shili.pojo.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    /*新增保存*/
    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    /*分页查询*/
    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    Type updateType(Long id, Type type);

    void deleteType(Long id);


}
