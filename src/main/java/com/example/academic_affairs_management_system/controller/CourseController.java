package com.example.academic_affairs_management_system.controller;



import com.baomidou.mybatisplus.extension.api.R;
import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.entity.Course;
import com.example.academic_affairs_management_system.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/course")
public class CourseController {
    @Autowired ICourseService iCourseService;

    @GetMapping("/getratio")
    public Result getratio(@RequestParam String courseid){
        return Result.success(iCourseService.getratio(courseid));
    }
}
