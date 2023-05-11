package com.example.academic_affairs_management_system.mapper;

import com.example.academic_affairs_management_system.controller.dto.AdminPack.StudentManage;
import com.example.academic_affairs_management_system.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    public List<StudentManage> manageList(String keyword);

    @Update("Update student set " +
            "student.name = #{s.studentName}, " +
            "sex = #{s.sex}, " +
            "home = #{s.home}, " +
            "phone = #{s.phone}, " +
            "deptId = (select deptId from dept where deptName = #{s.deptName} " +
            "where studentId = #{s.studentId}")
    public boolean manageEditStu(StudentManage s);
}
