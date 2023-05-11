package com.example.academic_affairs_management_system.service;

import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.Rank;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.Score;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.delScore;
import com.example.academic_affairs_management_system.controller.dto.StudentPack.StuScore;
import com.example.academic_affairs_management_system.controller.dto.TeacherPack.Student;
import com.example.academic_affairs_management_system.entity.Selectcourse;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
public interface ISelectcourseService extends IService<Selectcourse> {
    public List<Selectcourse> getAllInfo(String semester, String courseId, String staffId, String classTime) ;

    public List<Selectcourse> selectAll();

    public List<Score> getAllScore(int pagenum,int pagesize);

    public List<Score> getCourseScore(String semester, String courseId, String courseName);


    public List<Score> getPage(String semester,String studentId,String studentName,String courseId, String courseName);

    public List<StuScore> getStuPage(String semester,String studentId);

    List<Student> select_stu(int pagenum, int pagesize, String semester, String courseid, String staffid, String classtime);

    public List<StuScore> getStuScore(Integer pageNum, Integer pageSize, String semester, String studentId);

    public List<Score> getScore(Integer pageNum,Integer pageSize, String semester, String studentId, String studentName, String courseId, String courseName);

    public Result AdminDelScore(List<delScore> delList);

    public List<Rank> getCourseRank(Integer pageNum, Integer pageSize, String semester, String courseId, String courseName,String isPage);

    public List<Rank> getDeptRank(Integer pageNum, Integer pageSize,String semester,String deptName,String isPage);
    public Result updateScore(List<Student> LS);
}
