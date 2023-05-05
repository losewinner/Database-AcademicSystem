package com.example.academic_affairs_management_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.entity.Opencourse;
import com.example.academic_affairs_management_system.entity.Selectcourse;
import com.example.academic_affairs_management_system.service.IOpencourseService;
import com.example.academic_affairs_management_system.service.ISelectcourseService;
import com.example.academic_affairs_management_system.service.ISemestatusService;
import com.example.academic_affairs_management_system.service.impl.OpencourseServiceImpl;
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
@RequestMapping("/opencourse")
public class OpencourseController {

    @Autowired
    private IOpencourseService iOpencourseService;
    @Autowired
    private ISemestatusService isemestatusService;
    @GetMapping("/getclass")
    public Result select_class(@RequestParam String staffid){
        /**
         * 查询某老师所有班
         */
        String semester = isemestatusService.getnowsemester();
        
        return Result.success(iOpencourseService.select_class(staffid,semester));
    }
}
