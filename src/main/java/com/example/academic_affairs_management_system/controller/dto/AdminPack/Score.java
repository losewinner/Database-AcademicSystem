package com.example.academic_affairs_management_system.controller.dto.AdminPack;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Score {
    private String semester;
    private String studentId;
    private String studentName;
    private String staffId;
    private String deptId;
    private String deptName;
    private String courseId;
    private String courseName;
    private String classTime;
    private Integer testScore;
    private Integer score;
    private Integer ratio;
    private int finalScore;
    private double scorePoint;

}
