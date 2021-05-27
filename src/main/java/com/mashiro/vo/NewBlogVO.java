package com.mashiro.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 14:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewBlogVO {
    private Long id;
    private String title;
    private Boolean privacy;
}
