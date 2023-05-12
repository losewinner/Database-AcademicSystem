package com.example.academic_affairs_management_system.service.impl;

import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.Rank;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.Score;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.delScore;
import com.example.academic_affairs_management_system.controller.dto.StudentPack.StuCourse;
import com.example.academic_affairs_management_system.controller.dto.StudentPack.StuScore;
import com.example.academic_affairs_management_system.controller.dto.TeacherPack.Student;
import com.example.academic_affairs_management_system.entity.Selectcourse;
import com.example.academic_affairs_management_system.mapper.SelectcourseMapper;
import com.example.academic_affairs_management_system.service.ISelectcourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.academic_affairs_management_system.service.ISemestatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
@Service
public class SelectcourseServiceImpl extends ServiceImpl<SelectcourseMapper, Selectcourse> implements ISelectcourseService {

    @Resource
    private SelectcourseMapper selectcourseMapper;
    @Autowired
    private ISemestatusService iSemestatusService;
    @Override
    public List<Selectcourse> getAllInfo(String semester,String courseId,String staffId,String classTime){
        return selectcourseMapper.getAllInfo(semester,courseId,staffId,classTime);
    }
    @Override
    public List<Selectcourse> selectAll(){
        return selectcourseMapper.selectAll();
    }

    @Override
    public List<Score> getAllScore(int pagenum,int pagesize){
        pagenum = (pagenum-1)*pagesize;
        return selectcourseMapper.getAllScore(pagenum,pagesize);
    }

    @Override
    public List<Score> getCourseScore(String semester, String courseId, String courseName){
        return selectcourseMapper.getCourseScore(semester,courseId,courseName);
    }

    @Override
    public List<Score> getPage(String semester, String studentId,String studentName,String courseId, String courseName){
        //获得想要的对应的数据的总数
        return selectcourseMapper.getPage(semester,studentId,studentName,courseId,courseName);
    }

    @Override
    public List<StuScore> getStuPage(String semester, String studentId){
        //获得想要的对应的数据的总数
        return selectcourseMapper.getStuPage(semester,studentId);
    }

    @Override
    public List<StuCourse> getAllStuCourse(String semester, String studentId){
        //获得想要的对应的数据的总数
        return selectcourseMapper.getAllStuCourse(semester,studentId);
    }

    @Override
    public List<Student> select_stu(int pagenum, int pagesize,String semester, String courseid, String staffid, String classtime) {
        pagenum = (pagenum-1)*pagesize;
        return  selectcourseMapper.select_stu(pagenum,pagesize,semester,courseid,staffid,classtime);
    }


    @Override
    public List<StuScore> getStuScore(Integer pageNum, Integer pageSize, String semester, String studentId){
        Integer pageCurrent = (pageNum-1)*pageSize;
        return selectcourseMapper.getStuScore(pageCurrent,pageSize,semester, studentId);
    }

    @Override
    public List<StuCourse> getStuSelPage(String semester, String courseId, String courseName){
        //获得想要的对应的数据的总数
        return selectcourseMapper.getStuSelPage(semester,courseId,courseName);
    }

    @Override
    public List<StuCourse> getStuSelCourse(Integer pageNum, Integer pageSize, String semester, String courseId, String courseName){
        Integer pageCurrent = (pageNum-1)*pageSize;
        return selectcourseMapper.getStuSelCourse(pageCurrent,pageSize,semester, courseId, courseName);
    }

    @Override
    public Result changeNum(Integer num, String semester, String courseId, String staffId, String classTime) {
        if(!selectcourseMapper.changeNum(num, semester, courseId, staffId, classTime)) return Result.fail("更新失败");
        else return Result.success();
    }

    @Override
    public Result dropCourse(String studentId, String semester, String courseId, String staffId, String classTime) {
        if(!selectcourseMapper.dropCourse(studentId, semester, courseId, staffId, classTime)) return Result.fail("更新失败");
        else return Result.success();
    }

    @Override
    public Result selectCourse(String studentId, String semester, String courseId, String staffId, String classTime) {
        if(!selectcourseMapper.selectCourse(studentId, semester, courseId, staffId, classTime)) return Result.fail("更新失败");
        else return Result.success();
    }

    @Override
    public List<Score> getScore(Integer pageNum,Integer pageSize, String semester, String studentId, String studentName, String courseId, String courseName){
        Integer pageCurrent = (pageNum-1)*pageSize;
        return selectcourseMapper.getScore(pageCurrent,pageSize,semester, studentId, studentName, courseId, courseName);
    }

    @Override
    public Result AdminDelScore(List<delScore> delList){
        System.out.println("删除成绩"+ delList);
        //boolean success = true;
        for(delScore del:delList) {
            boolean success = selectcourseMapper.AdminDelScore(del.getSemester(),del.getStudentId(),del.getCourseId(),del.getStaffId());
            if (success) {
                continue;
            } else {
                return Result.fail("删除失败");
            }
        }
        return Result.success();
    }

    @Override
    public List<Rank> getCourseRank(Integer pageNum, Integer pageSize, String semester, String courseId, String courseName,String isPage){

        Integer pageCurrent = (pageNum-1)*pageSize;
        return selectcourseMapper.getCourseRank(pageCurrent,pageSize,semester, courseId, courseName, isPage);
    }

    @Override
    public List<Rank> getDeptRank(Integer pageNum, Integer pageSize,String semester,String deptName,String isPage){
        Integer pageCurrent = (pageNum-1)*pageSize;
        return selectcourseMapper.getDeptRank(pageCurrent,pageSize,semester,deptName,isPage);
    }
    @Override
    public Result updateScore(List<Student> LS) {
        String semester =  iSemestatusService.getnowsemester();
        for (Student S: LS) {
            System.out.println(S);
            if(!selectcourseMapper.updateScore(S,semester)) return Result.fail("更新失败");
        }
        return Result.success();
    }
}
