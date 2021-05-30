package com.mashiro.mapper;

import com.mashiro.entity.ExceptionLog;
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
public interface ExceptionLogMapper {

    /**
    * 新增异常日志
    * @param log
    * @return {@link int}
    * @author Mashiro
    * @date 2021/5/30 14:28
    */
    int saveExceptionLog(ExceptionLog log);

    /**
    * 删除日志
    * @param id
    * @return {@link int}
    * @author Mashiro
    * @date 2021/5/30 14:28
    */
    int deleteExceptionLogById(Long id);

    /**
    * 查询日志
    * @param startDate
    * @param endDate
    * @return {@link List< ExceptionLog>}
    * @author Mashiro
    * @date 2021/5/30 14:28
    */
    List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate);

}
