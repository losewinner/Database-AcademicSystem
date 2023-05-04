package com.example.academic_affairs_management_system.service;

import com.example.academic_affairs_management_system.controller.dto.AdminPack.Score;
import com.example.academic_affairs_management_system.entity.Selectcourse;
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
public interface ISelectcourseService extends IService<Selectcourse> {
    public List<Selectcourse> getAllInfo(String semester, String courseId, String staffId, String classTime) ;

    public List<Selectcourse> selectAll();

    public List<Score> getCourseScore(String semester, String courseId, String courseName);

    public List<Score> getStudentScore(String semester,String studentId,String studentName);
}
