package com.example.academic_affairs_management_system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class Login implements Serializable {
    String id;

    String password;

    int identify;

}
