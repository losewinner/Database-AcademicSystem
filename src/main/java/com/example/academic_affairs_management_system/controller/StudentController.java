package com.example.academic_affairs_management_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.academic_affairs_management_system.common.QueryPageParam;
import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.academic_affairs_management_system.entity.Student;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
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
    public Result manageEditStu(@RequestBody QueryPageParam queryPageParam){
        HashMap param = queryPageParam.getParam();
        System.out.println("学生信息"+param);
        String studentId = param.get("studentId").toString();
        String studentName = param.get("studentName").toString();
        String sex = param.get("sex").toString();
        String datebirth = param.get("birth").toString();
        System.out.println(datebirth);
        //这里还需要对birth进行类型转换操作，转为LocalDate的类型
        LocalDate birth = LocalDate.parse(datebirth);


        String home = param.get("home").toString();
        String phone = param.get("phone").toString();
        String deptName = param.get("deptName").toString();

        boolean success = iStudentService.manageEditStu(studentId,studentName,sex,birth,home,phone,deptName);
        if(success){
            return Result.success();
        }
        return Result.fail("修改失败");

    }

    @GetMapping("/getStu")
    public Result getStu(@RequestParam String studentId){
        QueryWrapper queryWrapper = new QueryWrapper<Student>();
        // System.out.println(studentId);
        queryWrapper.eq("studentId",studentId);
        Student student = iStudentService.getOne(queryWrapper);
        return Result.success(student);
    }
}
