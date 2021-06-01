package com.mashiro.service;

import com.mashiro.dto.VisitLogUuidTimeDTO;
import com.mashiro.entity.Visitor;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 15:58
 */
public interface VisitorService {

    @Async
    void saveVisitor(Visitor visitor);

    void deleteVisitor(Long id, String uuid);

    void updatePVAndLastTimeByUUID(VisitLogUuidTimeDTO dto);

    boolean hasUUID(String uuid);

    List<Visitor> getVisitorListByDate(String startDate, String endDate);

    List<String> getNewVisitorIpSourceByYesterday();


}
