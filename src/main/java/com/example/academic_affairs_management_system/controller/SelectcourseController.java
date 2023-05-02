package com.example.academic_affairs_management_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.entity.Selectcourse;
import com.example.academic_affairs_management_system.entity.Student;
import com.example.academic_affairs_management_system.service.ISelectcourseService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
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

    @GetMapping("/getstudent")
    public Result select_stu(Selectcourse selectcourse){
        /**
         * 查询某班级所有学生
         */
        LambdaQueryWrapper<Selectcourse> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Selectcourse::getSemester,selectcourse.getSemester())
                .eq(Selectcourse::getCourseid,selectcourse.getCourseid())
                .eq(Selectcourse::getStaffid,selectcourse.getStaffid())
                .eq(Selectcourse::getClasstime,selectcourse.getClasstime());
       return Result.success(iSelectcourseService.list(lambdaQueryWrapper));
    }






}
