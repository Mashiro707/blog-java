package com.mashiro.mapper;

import com.mashiro.entity.VisitRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/27 21:45
 */
@Mapper
@Repository
public interface VisitRecordMapper {

    int saveVisitRecord(VisitRecord visitRecord);

    /**
    * 按天数查询访问记录
    * @param limit
    * @return {@link List< VisitRecord>}
    * @author Mashiro
    * @data 2021/5/28 14:26
    *
    */
    List<VisitRecord> getVisitRecordListByLimit(Integer limit);


}
