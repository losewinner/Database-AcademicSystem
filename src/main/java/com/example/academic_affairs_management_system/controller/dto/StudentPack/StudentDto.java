package com.example.academic_affairs_management_system.controller.dto.StudentPack;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class StudentDto {
    private String studentid;
    private String name;
    private String sex;
    private LocalDate birth;
    private String home;
    private String phone;
    private String deptid;
    private String token;
}
