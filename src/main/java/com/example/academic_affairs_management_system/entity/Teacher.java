package com.example.academic_affairs_management_system.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("staffid")
    private String staffid;

    private String teachername;

    private String sex;

    private LocalDate birth;

    private String title;

    @TableField("deptid")
    private String deptid;


}
