package com.example.academic_affairs_management_system.service;

import com.example.academic_affairs_management_system.controller.dto.TeacherPack.Course;
import com.example.academic_affairs_management_system.entity.Opencourse;
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
public interface IOpencourseService extends IService<Opencourse> {
    List<Course> select_class(String staffid, String semester);

}
