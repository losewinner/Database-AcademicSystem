package com.example.academic_affairs_management_system.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.academic_affairs_management_system.common.Result;
import org.springframework.stereotype.Service;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.StudentManage;
import com.example.academic_affairs_management_system.entity.Student;
import com.example.academic_affairs_management_system.mapper.StudentMapper;
import com.example.academic_affairs_management_system.service.IStudentService;


import javax.annotation.Resource;
import java.time.LocalDate;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    public Student getstuInfo(String studentId) {
        QueryWrapper queryWrapper = new QueryWrapper<Student>();
        // System.out.println(studentId);
        queryWrapper.eq("studentId",studentId);
        Student student = getOne(queryWrapper);
        return student;
    }

    @Resource
    private StudentMapper studentMapper;
    @Override
    public List<StudentManage> manageList(String keyword){
        return studentMapper.manageList(keyword);
    }

    @Override
    public boolean manageEditStu(String studentId, String studentName, String sex,LocalDate birth, String home, String phone, String deptName){
        return studentMapper.manageEditStu(studentId,studentName,sex,birth,home,phone,deptName);
    }


    @Override
    public Result changeStuPassword(String studentId,String input) {
        if(!studentMapper.changeStuPassword(studentId, input)) return Result.fail("更新失败");
        else return Result.success();
    }

    @Override
    public Result changeStuHome(String studentId,String input) {
        if(!studentMapper.changeStuHome(studentId, input)) return Result.fail("更新失败");
        else return Result.success();
    }

    @Override
    public Result changeStuPhone(String studentId,String input) {
        if(!studentMapper.changeStuPhone(studentId, input)) return Result.fail("更新失败");
        else return Result.success();
    }

}
