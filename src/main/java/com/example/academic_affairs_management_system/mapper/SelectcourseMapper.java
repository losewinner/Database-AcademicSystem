package com.example.academic_affairs_management_system.mapper;

import com.example.academic_affairs_management_system.entity.Selectcourse;
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
public interface SelectcourseMapper extends BaseMapper<Selectcourse> {
    @Select("select studentId,semester,courseId,staffId,classTime,testScore,signScore,homeworkScore,score from selectcourse " +
            "where semester=#{semester} and courseId=#{courseId} and staffId=#{staffId} and classTime=#{classTime}")
    List<Selectcourse> getAllInfo(String semester,String courseId,String staffId,String classTime);

    public List<Selectcourse> selectAll();

    //统计分数
    @Select("SELECT" +
            "selectcourse.semester," +
            "selectcourse.studentId,student.name," +
            "selectcourse.staffId,teacher.name," +
            "selectcourse.courseId,course.name," +
            "testScore" +
            "FROM selectcourse,course,student,teacher" +
            "WHERE" +
            "selectcourse.courseId = course.courseId" +
            "AND student.studentId = selectcourse.studentId" +
            "AND teacher.staffId = selectcourse.staffId")
    List<Selectcourse> getScore(String semester,String studentId,String studentName,
                                String staffId,String staffName,
                                String courseId,String courseName);
}
