package com.example.academic_affairs_management_system.controller;


import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.entity.Teacher;
import com.example.academic_affairs_management_system.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private ITeacherService iTeacherService;

    @GetMapping("/getTeaDepList")
    public Result getTeaDepList(@RequestParam String deptName){
        List<Teacher> teacherList = new ArrayList<>();
        teacherList = iTeacherService.getTeaDepList(deptName);
        return Result.success(teacherList,teacherList.size());
    }

    @GetMapping("/getTea")
    public Result getTea(@RequestParam String staffId){
        Teacher teacher = iTeacherService.getteacInfo(staffId);
        return Result.success(teacher);
    }

}
