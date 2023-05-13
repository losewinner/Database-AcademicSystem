package com.example.academic_affairs_management_system.mapper;

import com.example.academic_affairs_management_system.controller.dto.AdminPack.CourseDto;
import com.example.academic_affairs_management_system.entity.Course;
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
public interface CourseMapper extends BaseMapper<Course> {

    @Select("select ratio from course where courseId=#{course}")
    int getratio(String courseid);

    public List<CourseDto> getCourseDto();
}
