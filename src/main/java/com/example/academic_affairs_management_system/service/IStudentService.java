package com.example.academic_affairs_management_system.service;

import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.StudentManage;
import com.example.academic_affairs_management_system.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.academic_affairs_management_system.mapper.StudentMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
public interface IStudentService extends IService<Student> {


    public List<StudentManage> manageList(String keyword);

    public boolean manageEditStu(String studentId, String studentName, String sex,LocalDate birth,String home,String phone, String deptName);


    public Result changeStuPassword(String studentId, String input);

    public Result changeStuHome(String studentId,String input);

    public Result changeStuPhone(String studentId,String input);

}
