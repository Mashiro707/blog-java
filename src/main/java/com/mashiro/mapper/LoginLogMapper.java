package com.mashiro.mapper;

import com.mashiro.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 8:29
 */
@Mapper
@Repository
public interface LoginLogMapper {

    List<LoginLog> getLoginLogByDate(String startDate, String endDate);

    int saveLoginLog(LoginLog log);

    int deleteLoginLog(Long id);
}
