package com.mashiro.service;

import com.mashiro.entity.LoginLog;

import java.util.List;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 8:53
 */
public interface LoginLogService {

    List<LoginLog> getLoginLogByDate(String startDate, String endDate);

    void saveLoginLog(LoginLog log);

    void deleteLoginLog(Long id);

}
