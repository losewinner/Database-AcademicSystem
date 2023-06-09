package com.example.academic_affairs_management_system.mapper;

import com.example.academic_affairs_management_system.controller.dto.AdminPack.Rank;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.Score;
import com.example.academic_affairs_management_system.controller.dto.StudentPack.StuCourse;
import com.example.academic_affairs_management_system.controller.dto.StudentPack.StuScore;
import com.example.academic_affairs_management_system.controller.dto.TeacherPack.Student;
import com.example.academic_affairs_management_system.entity.Opencourse;
import com.example.academic_affairs_management_system.entity.Selectcourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.sql.Statement;
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
    @Select("select studentId,semester,courseId,staffId,classTime,testScore,score from selectcourse " +
            "where semester=#{semester} and courseId=#{courseId} and staffId=#{staffId} and classTime=#{classTime}")
    List<Selectcourse> getAllInfo(String semester,String courseId,String staffId,String classTime);

    public List<Selectcourse> selectAll();

    public List<Score> getAllScore(int pageCurrent,int pageSize);

    public List<Score> getPage(String semester, String studentId, String studentName, String courseId, String courseName);

    List<StuScore> getStuPage(String semester, String studentId);

    @Select("select student.studentid,student.name,score,testscore,classtime,courseid,staffid " +
            "from selectcourse,student "+
            "where selectcourse.studentid = student.studentid and " +
            "semester = #{semester} and " +
            "courseid = #{courseid} and " +
            "staffid = #{staffid} and " +
            "classtime = #{classtime} " +
            "limit #{pagenum},#{pagesize}"
    )
    List<Student> select_stu(int pagenum, int pagesize, String semester, String courseid, String staffid, String classtime);

    //统计分数,按照课程来统计，不需要搜学生和老师，但是学生的姓名和学号还是要查的 （不需要此函数了）
    @Select("SELECT " +
            "selectcourse.semester,selectcourse.studentId,student.name as studentName,selectcourse.courseId,course.name as courseName,testScore " +
            "FROM selectcourse,course,student " +
            "WHERE " +
            "selectcourse.courseId = course.courseId " +
            "AND student.studentId = selectcourse.studentId " +
            "AND semester = #{semester} " +
            "AND selectcourse.courseId = #{courseId} " +
            "AND course.name = #{courseName}")
    List<Score> getCourseScore(String semester, String courseId, String courseName);

    //统计分数，按照学生来统计，展示的是一个学生（或者同名学生）的所有一个学期的课程总成绩 （不需要此函数了）
    @Select("SELECT " +
            "selectcourse.semester,selectcourse.studentId,student.name as studentName,selectcourse.courseId,course.name as courseName,testScore " +
            "FROM selectcourse,course,student " +
            "WHERE " +
            "selectcourse.courseId = course.courseId " +
            "AND student.studentId = selectcourse.studentId " +
            "AND semester = #{semester} " +
            "AND selectcourse.studentId = #{studentId} " +
            "AND student.name = #{studentName}")
    List<Score> getStudentScore(String semester, String studentId,String studentName);

    public List<StuScore> getStuScore(Integer pageCurrent, Integer pageSize, String semester, String studentId);

    public List<StuCourse> getStuSelPage(String semester, String courseId, String courseName);

    public List<StuCourse> getStuSelCourse(Integer pageCurrent, Integer pageSize, String semester, String courseId, String courseName);

    public List<StuCourse> getAllStuCourse(String semester, String studentId);

    @Update("UPDATE opencourse " +
            "SET remnant = remnant + #{num} " +
            "where " +
            "opencourse.semester = #{semester} " +
            "and opencourse.courseId = #{courseId} " +
            "and opencourse.staffId = #{staffId} " +
            "and opencourse.classTime = #{classTime}" )
    boolean changeNum(Integer num, String semester, String courseId, String staffId, String classTime);

    @Delete("DELETE FROM selectcourse " +
            "where " +
            "selectcourse.studentId = #{studentId} " +
            "and selectcourse.semester = #{semester} " +
            "and selectcourse.courseId = #{courseId} " +
            "and selectcourse.staffId = #{staffId} " +
            "and selectcourse.classTime = #{classTime}" )
    boolean dropCourse(String studentId, String semester, String courseId, String staffId, String classTime);

    @Insert("INSERT INTO selectcourse (studentId, semester, courseId, staffId, classTime) " +
            "VALUES " +
            "(#{studentId}, #{semester}, #{courseId}, #{staffId}, #{classTime})" )
    boolean selectCourse(String studentId, String semester, String courseId, String staffId, String classTime);

    public List<Score> getScore(Integer pageCurrent,Integer pageSize,String semester, String studentId, String studentName, String courseId, String courseName);


    boolean AdminDelScore(String semester,String studentId,String courseId,String staffId);

    List<Rank> getCourseRank(Integer pageCurrent,Integer pageSize,String semester, String courseId, String courseName, String isPage);

    List<Rank> getDeptRank(Integer pageCurrent, Integer pageSize,String semester,String deptName,String isPage);

    boolean updateScore(Student S,String semester);


//    @Update("Update selectcourse " +
//            "set score = IFNULL(score,0), testScore = IFNULL(testScore,0) " +
//            "where semester = #{semester} ")
//    public boolean setNullScore(String semester);

    @Select("CALL updateNullToZero(#{semester})")
    @Options(statementType = StatementType.CALLABLE)
    public void setNullScore(String semester);

}
