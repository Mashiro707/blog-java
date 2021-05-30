package com.mashiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description: 访客更新DTO
 * @Author: BeforeOne
 * @Date: Created in 2021/5/27 21:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitLogUuidTimeDTO {
    private String uuid;
    private Date time;
    private Integer pv;
}
