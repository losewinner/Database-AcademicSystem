package com.example.academic_affairs_management_system.mapper;

import com.example.academic_affairs_management_system.controller.dto.TeacherPack.Course;
import com.example.academic_affairs_management_system.entity.Opencourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.academic_affairs_management_system.entity.Selectcourse;
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
public interface OpencourseMapper extends BaseMapper<Opencourse> {
    @Select("select classtime,course.name as coursename,course.courseId from course,opencourse " +
            "where course.courseId=opencourse.courseId and " +
            "semester = #{semester} and staffid = #{staffid}")
    public List<Course> select_class(String staffid, String semester);

}
