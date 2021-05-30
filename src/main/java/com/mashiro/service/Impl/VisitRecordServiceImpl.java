package com.mashiro.service.Impl;

import com.mashiro.entity.VisitRecord;
import com.mashiro.mapper.VisitRecordMapper;
import com.mashiro.service.VisitRecordService;
import org.springframework.stereotype.Service;

/**
 * @Description: 访问记录业务层实现
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 16:42
 */
@Service
public class VisitRecordServiceImpl implements VisitRecordService {

    private final VisitRecordMapper visitRecordMapper;

    public VisitRecordServiceImpl(VisitRecordMapper visitRecordMapper) {
        this.visitRecordMapper = visitRecordMapper;
    }

    @Override
    public void saveVisitRecord(VisitRecord visitRecord) {
        visitRecordMapper.saveVisitRecord(visitRecord);
    }
}
