package com.mashiro.service;

import java.util.Map;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 14:10
 */
public interface AboutService {

    void updateAbout(Map<String, String> map);

    boolean getAboutCommentEnabled();

    Map<String, String> getAboutInfo();

    Map<String, String> getAboutSetting();
}
