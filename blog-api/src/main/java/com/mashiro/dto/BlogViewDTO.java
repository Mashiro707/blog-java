package com.mashiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 14:44
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BlogViewDTO {
    private Long id;
    private Integer views;
}
