package com.mashiro.service.Impl;

import com.mashiro.entity.CityVisitor;
import com.mashiro.mapper.CityVisitorMapper;
import com.mashiro.service.CityVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 城市访客数量统计业务层实现
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 17:35
 */
@Service
public class CityVisitorServiceImp implements CityVisitorService {

    @Autowired
    private CityVisitorMapper cityVisitorMapper;

    @Override
    public void saveCityVisitor(CityVisitor cityVisitor) {
        cityVisitorMapper.saveCityVisitor(cityVisitor);
    }
}
