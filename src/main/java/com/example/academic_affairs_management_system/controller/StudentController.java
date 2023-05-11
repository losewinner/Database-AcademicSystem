package com.example.academic_affairs_management_system.controller;


import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.academic_affairs_management_system.entity.Student;
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
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService iStudentService;

    @GetMapping("/list")
    public List<Student>list(){
        return iStudentService.list();
    }

    @GetMapping("/manageList")
    public Result manageList(@RequestParam String keyword){
        return Result.success(iStudentService.manageList(keyword),iStudentService.manageList(keyword).size());
    }
}
