package com.example.academic_affairs_management_system.mapper;

import com.example.academic_affairs_management_system.controller.dto.AdminPack.OpenCourseDto;
import com.example.academic_affairs_management_system.controller.dto.TeacherPack.Course;
import com.example.academic_affairs_management_system.entity.Opencourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.academic_affairs_management_system.entity.Selectcourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
public interface OpencourseMapper extends BaseMapper<Opencourse> {
    @Select("select classtime,course.name as coursename,course.courseId,address " +
            "from course,opencourse " +
            "where course.courseId=opencourse.courseId and " +
            "semester = #{semester} and staffid = #{staffid}")
    public List<Course> select_class(String staffid, String semester);

    public boolean insertNewCourse(String semester,String courseId,String staffId,String classTime);

    @Update("Update opencourse " +
            "set classTime = #{classTime}, " +
            "volume = #{volume}, " +
            "remnant = #{remnant} " +
            "where " +
            "semester = #{semester} " +
            "and courseId = #{courseId} " +
            "and staffId = #{staffId} ")
    public boolean updateOpenCou(String semester,String courseId,String staffId,String classTime,int volume,int remnant);

    public List<OpenCourseDto> getNowSemCourse(String semester);

    @Select("select classTime " +
            "from opencourse " +
            "where semester = #{semester} " +
            "and staffId = #{staffId} " +
            "and courseId !=#{courseId} ")
    public List<Opencourse> getTeaClassTime(String semester,String staffId,String courseId);
}
