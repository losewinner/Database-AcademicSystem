package com.example.academic_affairs_management_system.service.impl;

import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.entity.Course;
import com.example.academic_affairs_management_system.mapper.CourseMapper;
import com.example.academic_affairs_management_system.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
}
