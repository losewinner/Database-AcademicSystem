package com.example.academic_affairs_management_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.academic_affairs_management_system.common.QueryPageParam;
import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.Score;
import com.example.academic_affairs_management_system.controller.dto.TeacherPack.Student;
import com.example.academic_affairs_management_system.entity.Selectcourse;
import com.example.academic_affairs_management_system.service.ISelectcourseService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
@RestController
@RequestMapping("/selectcourse")
public class SelectcourseController {

    @Autowired
    private ISelectcourseService iSelectcourseService;

    @GetMapping("/allstudent")
    public Result findPage(@RequestParam String semester,
                           @RequestParam String courseId,
                           @RequestParam String staffId,
                           @RequestParam String classTime ){
        return Result.success(iSelectcourseService.getAllInfo(semester,courseId,staffId,classTime));
    }
    @GetMapping("/list")
    public List<Selectcourse> selectAll(){
        return iSelectcourseService.selectAll();
    }
    @PostMapping("/modifyscore")
    public Result modify_sorce(Selectcourse selectcourse){
        return Result.success(iSelectcourseService.saveOrUpdate(selectcourse));
    }

    @PostMapping("/getstudent")
    public Result select_stu(@RequestBody QueryPageParam queryPageParam) {
        int pagenum = queryPageParam.getPagenum(),pagesize=queryPageParam.getPagesize();
        HashMap param= queryPageParam.getParam();
        List<Student> data = iSelectcourseService.select_stu(pagenum,pagesize,
                param.get("semester").toString(),param.get("courseid").toString(),
                param.get("staffid").toString(),param.get("classtime").toString());

        return Result.success(data,data.size());
    }


//    @PostMapping("/getstudent")
//    public Result select_stu(@RequestBody QueryPageParam queryPageParam){
//        /**
//         * 查询某班级所有学生
//         */
//        int pagenum = queryPageParam.getPagenum(),pagesize=queryPageParam.getPagesize();
//        Page<Selectcourse> page = new Page<>();
//        page.setOptimizeCountSql(false);
//        page.setCurrent(pagenum);
//        page.setSize(pagesize);
//
//        HashMap param= queryPageParam.getParam();
//
//        LambdaQueryWrapper<Selectcourse> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(Selectcourse::getSemester,param.get("semester"))
//                .eq(Selectcourse::getCourseid,param.get("courseid"))
//                .eq(Selectcourse::getStaffid,param.get("staffid"))
//                .eq(Selectcourse::getClasstime,param.get("classtime"));
//
//        IPage result = iSelectcourseService.page(page,lambdaQueryWrapper);
//
//        Long total = result.getTotal();
//
//        return Result.success(result.getRecords(),(total- (long) pagenum *pagesize-1+pagesize)%pagesize+1);
//    }

    @GetMapping("/getcoursescore")
    public List<Score>getCourseScore(@RequestParam String semester,
                                     @RequestParam String courseId,
                                     @RequestParam String courseName){
        return iSelectcourseService.getCourseScore(semester,courseId,courseName);
    }

    @GetMapping("/getstudentscore")
    public List<Score>getStudentScore(@RequestParam String semester,
                                       @RequestParam String studentId,
                                       @RequestParam String studentName){
        return iSelectcourseService.getStudentScore(semester, studentId, studentName);
    }

    @GetMapping("/getScore")
    public List<Score> getScore(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam String semester,
                                @RequestParam String studentId,
                                @RequestParam String studentName,
                                @RequestParam String courseId,
                                @RequestParam String courseName){
        //Integer pageNum = page.get("pageNum");
        //Integer pageSize = page.get("pageSize");
        return iSelectcourseService.getScore(pageNum,pageSize,semester,studentId,studentName,courseId,courseName);

    }






}
