package com.example.academic_affairs_management_system.controller.dto.AdminPack;

import lombok.Data;

@Data
public class Score {
    private String semester;
    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;
    private Integer testScore;
    private Integer score;
    private Integer ratio;
    private Double finalScore;
    private Double scorePoint;
}
