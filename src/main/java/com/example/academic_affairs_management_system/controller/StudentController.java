package com.example.academic_affairs_management_system.controller;


import cn.hutool.core.date.DateTime;
import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.StudentManage;
import com.example.academic_affairs_management_system.service.IStudentService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.academic_affairs_management_system.entity.Student;

import java.time.LocalDateTime;
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
    @PostMapping("/manageEditStu")
    public Result manageEditStu(@RequestBody StudentManage student) {
        boolean success = iStudentService.manageEditStu(student);
        if (success) {
            return Result.success();
        }
        return Result.fail("修改失败");
    }
}
