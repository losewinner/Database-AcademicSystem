package com.example.academic_affairs_management_system.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Semestatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private String semester;

    /**
     * 学期状态：1：学期进行中；2：学期记录成绩（只有教师操作）；3：学期结束（只有学生查询）
     */
    private Integer status;


}
