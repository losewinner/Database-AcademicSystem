package com.example.academic_affairs_management_system.service.impl;

import com.example.academic_affairs_management_system.controller.dto.AdminPack.Score;
import com.example.academic_affairs_management_system.controller.dto.TeacherPack.Student;
import com.example.academic_affairs_management_system.entity.Selectcourse;
import com.example.academic_affairs_management_system.mapper.SelectcourseMapper;
import com.example.academic_affairs_management_system.service.ISelectcourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
@Service
public class SelectcourseServiceImpl extends ServiceImpl<SelectcourseMapper, Selectcourse> implements ISelectcourseService {

    @Resource
    private SelectcourseMapper selectcourseMapper;
    @Override
    public List<Selectcourse> getAllInfo(String semester,String courseId,String staffId,String classTime){
        return selectcourseMapper.getAllInfo(semester,courseId,staffId,classTime);
    }
    @Override
    public List<Selectcourse> selectAll(){
        return selectcourseMapper.selectAll();
    }


    @Override
    public List<Score> getCourseScore(String semester, String courseId, String courseName){
        return selectcourseMapper.getCourseScore(semester,courseId,courseName);
    }

    @Override
    public List<Score> getStudentScore(String semester, String studentId,String studentName){
        return selectcourseMapper.getStudentScore(semester,studentId,studentName);
    }

    @Override
    public List<Student> select_stu(int pagenum, int pagesize,String semester, String courseid, String staffid, String classtime) {
        pagenum = (pagenum-1)*pagesize;
        return  selectcourseMapper.select_stu(pagenum,pagesize,semester,courseid,staffid,classtime);
    }

    @Override
    public List<Score> getScore(Integer pageNum,Integer pageSize, String semester, String studentId, String studentName, String courseId, String courseName){
        Integer pageCurrent = (pageNum-1)*pageSize;
        return selectcourseMapper.getScore(pageCurrent,pageSize,semester, studentId, studentName, courseId, courseName);
    }

}
