package com.mashiro.service.Impl;

import com.mashiro.dto.VisitLogUuidTimeDTO;
import com.mashiro.entity.VisitLog;
import com.mashiro.exception.PersistenceException;
import com.mashiro.mapper.VisitLogMapper;
import com.mashiro.service.VisitLogService;
import com.mashiro.util.IpAddressUtils;
import com.mashiro.util.UserAgentUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description: 访问日志业务层实现
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 15:19
 */
@Service
public class VisitLogServiceImpl implements VisitLogService {

    private final VisitLogMapper visitLogMapper;
    private final UserAgentUtils userAgentUtils;

    public VisitLogServiceImpl(VisitLogMapper visitLogMapper, UserAgentUtils userAgentUtils) {
        this.visitLogMapper = visitLogMapper;
        this.userAgentUtils = userAgentUtils;
    }

    @Transactional
    @Override
    public void saveVisitLog(VisitLog log) {
        String ipSource = IpAddressUtils.getCityInfo(log.getIp());
        Map<String, String> userAgentMap = userAgentUtils.parseOsAndBrowser(log.getUserAgent());
        String os = userAgentMap.get("os");
        String browser = userAgentMap.get("browser");
        log.setIpSource(ipSource);
        log.setOs(os);
        log.setBrowser(browser);
        if (visitLogMapper.saveVisitLog(log) != 1) {
            throw new PersistenceException("日志添加失败");
        }
    }

    @Transactional
    @Override
    public void deleteVisitLogById(Long id) {
        if (visitLogMapper.deleteVisitLogById(id) != 1){
            throw new PersistenceException("删除日志失败");
        }
    }

    @Override
    public List<VisitLog> getVisitLogListByUUIDAndDate(String uuid, String startDate, String endDate) {
        return visitLogMapper.getVisitLogListByUUIDAndDate(uuid, startDate, endDate);
    }

    @Override
    public List<VisitLogUuidTimeDTO> getUUIDAndCreateTimeByYesterday() {
        return visitLogMapper.getUUIDAndCreateTimeByYesterday();
    }
}
