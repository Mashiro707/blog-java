package com.mashiro.mapper;

import com.mashiro.entity.SiteSetting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 站点设置持久层接口
 * @Author: Mashiro
 * @Date: Created in 2021/5/27 21:44
 */
@Mapper
@Repository
public interface SiteSettingMapper {

    /**
    * 添加站点设置
    * @param siteSetting 站点设置对象
    * @return {@link int}
    * @author Mashiro
    * @date 2021/5/30 10:01
     */
    int saveSiteSetting(SiteSetting siteSetting);

    /**
    * 删除站点信息
    * @param id 站点设置id
    * @return {@link int}
    * @author Mashiro
    * @date 2021/5/30 10:01
    */
    int deleteSiteSettingById(Integer id);

    /**
    * 更新站点信息
    * @param siteSetting 站点设置对象
    * @return {@link int}
    * @author Mashiro
    * @date 2021/5/30 10:01
    */
    int updateSiteSetting(SiteSetting siteSetting);

    //int updateFriendInfoContent(String content);

    //int updateFriendInfoCommentEnabled(Boolean commentEnabled);

    /**
    * 查询网页标题后缀
    * @param
    * @return {@link String}
    * @author Mashiro
    * @date 2021/5/30 10:05
    */
    String getWebTitleSuffix();

    /**
    * 查询站点设置
    * @param
    * @return {@link List< SiteSetting>}
    * @author Mashiro
    * @date 2021/5/30 10:05
    */
    List<SiteSetting> getSiteSettingList();

    //List<SiteSetting> getFriendInfo();



}
