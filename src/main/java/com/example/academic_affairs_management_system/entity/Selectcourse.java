package com.example.academic_affairs_management_system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class Selectcourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("studentId")
    private String studentid;

    private String semester;

    @TableField("courseId")
    private String courseid;

    @TableField("staffId")
    private String staffid;

    @TableField("classTime")
    private String classtime;

    private Integer score;

    @TableField("testScore")
    private Integer testscore;

    @TableField("signScore")
    private Integer signscore;

    @TableField("homeworkScore")
    private Integer homeworkscore;

    @TableField("finalScore")
    private float finalscore;

    @TableField("scorePoint")
    private float scorepoint;

}
