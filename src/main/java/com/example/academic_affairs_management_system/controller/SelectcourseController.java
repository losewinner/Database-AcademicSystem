package com.example.academic_affairs_management_system.controller;


import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.service.ISelectcourseService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
