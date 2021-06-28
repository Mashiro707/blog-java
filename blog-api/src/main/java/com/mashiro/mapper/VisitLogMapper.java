package com.mashiro.mapper;

import com.mashiro.dto.VisitLogUuidTimeDTO;
import com.mashiro.entity.VisitLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/27 21:44
 */
@Mapper
@Repository
public interface VisitLogMapper {

    int saveVisitLog(VisitLog log);

    int deleteVisitLogById(Long id);

    int countVisitLogByToday();

    List<VisitLog> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate);

    List<VisitLogUuidTimeDTO> getUUIDAndCreateTimeByYesterday();
}
