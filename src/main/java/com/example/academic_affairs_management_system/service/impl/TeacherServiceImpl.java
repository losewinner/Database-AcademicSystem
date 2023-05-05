package com.example.academic_affairs_management_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.academic_affairs_management_system.common.Constants;
import com.example.academic_affairs_management_system.entity.Teacher;
import com.example.academic_affairs_management_system.exception.ServiceException;
import com.example.academic_affairs_management_system.mapper.TeacherMapper;
import com.example.academic_affairs_management_system.service.ITeacherService;
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
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {
    public Teacher getteacInfo(String staffid) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        System.out.println(staffid.length());
        queryWrapper.eq("staffid",staffid);
        Teacher teacher = getOne(queryWrapper);
        return teacher;

    }

}
