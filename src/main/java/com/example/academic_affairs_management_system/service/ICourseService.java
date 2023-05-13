package com.example.academic_affairs_management_system.service;

import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.CourseDto;
import com.example.academic_affairs_management_system.entity.Course;
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
public interface ICourseService extends IService<Course> {
    int getratio(String courseid);

    public List<CourseDto> getCourseDto();
}
