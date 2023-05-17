package com.example.academic_affairs_management_system.service;


import com.example.academic_affairs_management_system.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
public interface ITeacherService extends IService<Teacher> {
    public Teacher getteacInfo(String staffid);

    public List<Teacher> getTeaDepList(String deptName);


}
