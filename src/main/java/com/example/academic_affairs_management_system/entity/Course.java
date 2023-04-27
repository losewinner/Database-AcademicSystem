package com.example.academic_affairs_management_system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("courseId")
    private String courseid;

    private String name;

    private Integer credit;

    private Integer creditHours;

    @TableField("deptId")
    private String deptid;

    /**
     * 课程平时成绩和考试成绩占比，比如1表示平时和考试是1：9
     */
    private Integer ratio;


}
