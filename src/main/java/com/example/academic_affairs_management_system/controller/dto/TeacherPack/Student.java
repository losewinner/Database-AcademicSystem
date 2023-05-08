package com.example.academic_affairs_management_system.controller.dto.TeacherPack;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Student {
    private String studentid;
    private String name;
    private Integer score;
    private Integer testscore;
    @TableField("finalScore")
    private Integer finalscore;
}
