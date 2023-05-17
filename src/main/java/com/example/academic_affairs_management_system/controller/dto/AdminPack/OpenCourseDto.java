package com.example.academic_affairs_management_system.controller.dto.AdminPack;

import lombok.Data;

@Data
public class OpenCourseDto {
    private String semester;
    private String courseId;
    private String courseName;
    private String staffId;
    private String teachername;
    private String classTime;
    private String address;
    private int volume;
    private int remnant;
}
