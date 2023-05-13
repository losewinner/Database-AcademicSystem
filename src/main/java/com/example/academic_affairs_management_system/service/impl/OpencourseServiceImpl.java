package com.example.academic_affairs_management_system.service.impl;

import com.example.academic_affairs_management_system.controller.dto.AdminPack.OpenCourseDto;
import com.example.academic_affairs_management_system.controller.dto.TeacherPack.Course;
import com.example.academic_affairs_management_system.entity.Opencourse;
import com.example.academic_affairs_management_system.mapper.OpencourseMapper;
import com.example.academic_affairs_management_system.mapper.SelectcourseMapper;
import com.example.academic_affairs_management_system.service.IOpencourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class OpencourseServiceImpl extends ServiceImpl<OpencourseMapper, Opencourse> implements IOpencourseService {

    @Resource
    private OpencourseMapper opencourseMapper;

    @Override
    public List<Course> select_class(String staffid, String semester) {
        return opencourseMapper.select_class(staffid,semester);
    }

    @Override
    public boolean insertNewCourse(String semester,String courseId,String staffId,String classTime){
        System.out.println("新增开课");
        return opencourseMapper.insertNewCourse(semester, courseId, staffId, classTime);

    }

    @Override
    public List<OpenCourseDto> getNowSemCourse(String semester){
        return this.opencourseMapper.getNowSemCourse(semester);
    }

    @Override
    public boolean updateOpenCou(String semester,String courseId,String staffId,String classTime,int volume,int remnant){
        return opencourseMapper.updateOpenCou(semester,courseId,staffId,classTime,volume,remnant);
    }
}
