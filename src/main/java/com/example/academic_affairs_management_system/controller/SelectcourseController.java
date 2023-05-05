package com.example.academic_affairs_management_system.controller;


import com.example.academic_affairs_management_system.common.QueryPageParam;
import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.Score;
import com.example.academic_affairs_management_system.controller.dto.TeacherPack.Student;
import com.example.academic_affairs_management_system.entity.Selectcourse;
import com.example.academic_affairs_management_system.service.ISelectcourseService;

import com.example.academic_affairs_management_system.service.ISemestatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @Autowired
    private ISemestatusService iSemestatusService;

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
        /*
         * 查询某班级所有学生
         */
        int pagenum = queryPageParam.getPagenum(),pagesize=queryPageParam.getPagesize();
        HashMap param= queryPageParam.getParam();
        String semester =  iSemestatusService.getnowsemester();
        System.out.println(param);
        System.out.println(pagenum);
        System.out.println(pagesize);
        param.get("courseid").toString();
        param.get("staffid").toString();
        param.get("classtime").toString();

        List<Student> data = iSelectcourseService.select_stu(pagenum,pagesize,
                semester,param.get("courseid").toString(),
                param.get("staffid").toString(),param.get("classtime").toString());

        return Result.success(data,data.size());
    }


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
