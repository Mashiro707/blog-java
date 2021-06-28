package com.mashiro.service;

import com.mashiro.entity.ExceptionLog;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 14:25
 */
public interface ExceptionLogService {

    @Async
    void saveExceptionLog(ExceptionLog log);

    void deleteExceptionLogById(Long id);

    List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate);

}
