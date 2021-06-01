package com.mashiro.service;

import com.mashiro.entity.SiteSetting;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Mashiro
 * @Date: Created in 2021/5/30 10:06
 */
public interface SiteSettingService {

    void updateSiteSetting(List<LinkedHashMap> siteSettings, List<Integer> deleteIds);

    String getWebTitleSuffix();

    Map<String, List<SiteSetting>> getList();

    Map<String, Object> getSiteInfo();

}
