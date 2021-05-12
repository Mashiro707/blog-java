package com.shili.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 底部信息展示
 * @Author: BeforeOne
 * @Date: Created in 2021/5/12 14:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogInfoVo {

    private Integer views;
    private Integer commentCount;

}
