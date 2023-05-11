package com.example.academic_affairs_management_system.controller.dto.StudentPack;

import lombok.Data;

@Data
public class StuCourse {
    private String semester;
    private String studentId;
    private String studentName;
    private String staffId;
    private String deptId;
    private String deptName;
    private String courseId;
    private String courseName;
    private String classTime;
    private Integer courseCredit;

}
