package com.example.academic_affairs_management_system.controller.dto.TeacherPack;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class TeacherDto {
    private String staffid;
    private String teachername;
    private String sex;
    private LocalDate birth;
    private String title;
    private String deptid;
    private String token;
}
