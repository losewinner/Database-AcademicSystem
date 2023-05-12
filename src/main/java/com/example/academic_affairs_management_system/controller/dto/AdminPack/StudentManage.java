package com.example.academic_affairs_management_system.controller.dto.AdminPack;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentManage {
    private String studentId;
    private String studentName;
    private String sex;
    private LocalDate birth;
    private String home;
    private String phone;
    private String deptName;
}
