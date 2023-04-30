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
}
