package com.example.academic_affairs_management_system.mapper;

import com.example.academic_affairs_management_system.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface TeacherMapper extends BaseMapper<Teacher> {

    public List<Teacher> getTeaDepList(String deptName);



}
