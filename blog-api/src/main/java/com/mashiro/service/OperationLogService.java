package com.mashiro.service;

import com.mashiro.entity.OperationLog;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 14:45
 */
public interface OperationLogService {

    @Async
    void saveOperationLog(OperationLog log);

    void deleteOperationLogById(Long id);

    List<OperationLog> getOperationLogListByDate(String startDate, String endDate);



}
