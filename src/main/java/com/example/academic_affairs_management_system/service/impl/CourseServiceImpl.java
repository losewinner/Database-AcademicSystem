package com.example.academic_affairs_management_system.service.impl;

import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.CourseDto;
import com.example.academic_affairs_management_system.entity.Course;
import com.example.academic_affairs_management_system.mapper.CourseMapper;
import com.example.academic_affairs_management_system.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired CourseMapper courseMapper;
    @Override
    public int getratio(String courseid) {
       return courseMapper.getratio(courseid);
    }

    @Override
    public List<CourseDto> getCourseDto() {
        return courseMapper.getCourseDto();
    }

    @Override
    public  boolean insertCourse(String courseId,String courseName,int credit,int creditHours,String deptId,int ratio){
        return courseMapper.insertCourse(courseId,courseName,credit,creditHours,deptId,ratio);
    }

    @Override
    public String getDeptId(String deptName){
        return courseMapper.getDeptId(deptName);
    }
}
