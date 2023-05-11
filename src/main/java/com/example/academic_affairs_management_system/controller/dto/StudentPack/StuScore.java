package com.example.academic_affairs_management_system.controller.dto.StudentPack;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class StuScore {
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
    private Integer testScore;
    private Integer score;
    private Integer ratio;
    private int finalScore;
    private double scorePoint;

}
