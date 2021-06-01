package com.mashiro.mapper;

import com.mashiro.entity.OperationLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/27 21:43
 */
@Mapper
@Repository
public interface OperationLogMapper {

    int saveOperationLog(OperationLog log);

    int deleteOperationLogById(Long id);

    List<OperationLog> getOperationLogListByDate(String startDate, String endDate);



}
