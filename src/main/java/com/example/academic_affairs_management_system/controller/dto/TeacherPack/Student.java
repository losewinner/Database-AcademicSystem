package com.example.academic_affairs_management_system.controller.dto.TeacherPack;

import lombok.Data;

@Data
public class Student {
    private String studentid;
    private String name;
    private Integer score;
    private Integer testscore;
    private Integer signscore;
    private Integer homeworkscore;
}
