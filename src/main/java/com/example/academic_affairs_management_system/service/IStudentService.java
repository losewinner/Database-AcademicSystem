package com.example.academic_affairs_management_system.service;

import com.example.academic_affairs_management_system.controller.dto.AdminPack.StudentManage;
import com.example.academic_affairs_management_system.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.academic_affairs_management_system.mapper.StudentMapper;

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


}
