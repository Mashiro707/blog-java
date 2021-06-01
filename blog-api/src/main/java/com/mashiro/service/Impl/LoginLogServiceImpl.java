package com.mashiro.service.Impl;

import com.mashiro.entity.LoginLog;
import com.mashiro.mapper.LoginLogMapper;
import com.mashiro.service.LoginLogService;
import com.mashiro.util.IpAddressUtils;
import com.mashiro.util.UserAgentUtils;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description: 登录日志业务层实现
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 8:54
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    private final LoginLogMapper loginLogMapper;
    private final IpAddressUtils ipAddressUtils;
    private final UserAgentUtils userAgentUtils;

    public LoginLogServiceImpl(LoginLogMapper loginLogMapper, IpAddressUtils ipAddressUtils, UserAgentUtils userAgentUtils) {
        this.loginLogMapper = loginLogMapper;
        this.ipAddressUtils = ipAddressUtils;
        this.userAgentUtils = userAgentUtils;
    }

    @Override
    public List<LoginLog> getLoginLogByDate(String startDate, String endDate) {
        return loginLogMapper.getLoginLogByDate(startDate, endDate);
    }

    @Transactional
    @Override
    public void saveLoginLog(LoginLog log) {
        String ipSource = ipAddressUtils.getCityInfo(log.getIp());
        Map<String, String> userAgentMap = userAgentUtils.parseOsAndBrowser(log.getUserAgent());
        String os= userAgentMap.get("os");
        String browser = userAgentMap.get("browser");
        log.setIpSource(ipSource);
        log.setOs(os);
        log.setBrowser(browser);
        if (loginLogMapper.saveLoginLog(log) != 1){
            throw new PersistenceException("日志添加失败");
        }
    }

    @Override
    public void deleteLoginLog(Long id) {
        if (loginLogMapper.deleteLoginLog(id) != 1){
            throw new PersistenceException("删除日志失败");
        }
    }
}
