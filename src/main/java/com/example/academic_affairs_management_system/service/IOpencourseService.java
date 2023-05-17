package com.example.academic_affairs_management_system.service;

import com.example.academic_affairs_management_system.controller.dto.AdminPack.OpenCourseDto;
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

    public boolean insertNewCourse(String semester,String courseId,String staffId,String classTime,String address);

    public boolean updateOpenCou(String semester,String courseId,String staffId,String classTime,String address,int volume,int remnant);

    public List<OpenCourseDto> getNowSemCourse(String semester);

    public List<Opencourse> getTeaClassTime(String semester,String staffId,String courseId);

    public List<Opencourse> getTimeAddress(String semester,String classTime);
}
