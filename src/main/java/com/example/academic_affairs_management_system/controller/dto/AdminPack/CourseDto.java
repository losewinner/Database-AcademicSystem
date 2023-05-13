package com.example.academic_affairs_management_system.controller.dto.AdminPack;

import lombok.Data;

@Data
public class CourseDto {
    private String courseId;

    private String courseName;

    private Integer credit;

    private Integer creditHours;

    private String deptName;

    /**
     * 课程平时成绩和考试成绩占比，比如1表示平时和考试是1：9
     */
    private Integer ratio;
}
