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
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("deptId")
    private String deptid;

    @TableField("deptName")
    private String deptname;

    private String address;

    @TableField("deptPhone")
    private String deptphone;


}
