package com.example.academic_affairs_management_system.controller.dto.AdminPack;

import lombok.Data;

@Data
public class Rank {
    private int rank;
    private String semester;
    private String studentId;
    private String studentName;
    private String deptName;
    private String courseName;
    private String courseId;
    private int finalScore;//单个课程成绩的和
    private int semeFinalScore;//一个学期的加权平均和的成绩
    private int uniFinalScore;//当前所有成绩的加权平均和的成绩
    private double scorePoint;
}
