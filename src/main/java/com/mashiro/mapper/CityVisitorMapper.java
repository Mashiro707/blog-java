package com.mashiro.mapper;

import com.mashiro.entity.CityVisitor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/27 21:42
 */
@Mapper
@Repository
public interface CityVisitorMapper {

    List<CityVisitor> getCityVisitorLis();

    int saveCityVisitor(CityVisitor cityVisitor);


}
