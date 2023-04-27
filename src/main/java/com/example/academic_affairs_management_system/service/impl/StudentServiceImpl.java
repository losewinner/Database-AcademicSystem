package com.example.academic_affairs_management_system.service.impl;

import com.example.academic_affairs_management_system.entity.Student;
import com.example.academic_affairs_management_system.mapper.StudentMapper;
import com.example.academic_affairs_management_system.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
