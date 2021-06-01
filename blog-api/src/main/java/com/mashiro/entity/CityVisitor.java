package com.mashiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 20:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityVisitor {
    private String city;//城市名称
    private Integer uv;//独立访客数量
}
