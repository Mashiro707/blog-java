package com.mashiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 20:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteSetting {
    private Long id;
    private String nameEn;
    private String nameZh;
    private String value;
    private Integer type;
}
