package com.example.academic_affairs_management_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.entity.Opencourse;
import com.example.academic_affairs_management_system.entity.Selectcourse;
import com.example.academic_affairs_management_system.service.IOpencourseService;
import com.example.academic_affairs_management_system.service.ISelectcourseService;
import com.example.academic_affairs_management_system.service.impl.OpencourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @GetMapping("/getclass")
    public Result select_class(Opencourse opencourse){
        /**
         * 查询某老师所有班
         */
        LambdaQueryWrapper<Opencourse> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Opencourse::getSemester,opencourse.getSemester())
                .eq(Opencourse::getStaffid,opencourse.getStaffid());
        return Result.success(iOpencourseService.list(lambdaQueryWrapper));
    }
}
