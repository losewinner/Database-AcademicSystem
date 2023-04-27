package com.example.academic_affairs_management_system.entity;

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
public class Opencourse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String semester;

    @TableField("courseId")
    private String courseid;

    @TableField("staffId")
    private String staffid;

    @TableField("classTime")
    private String classtime;

    private Integer volume;

    private Integer remnant;


}
