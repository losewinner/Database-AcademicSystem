package com.example.academic_affairs_management_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.academic_affairs_management_system.common.QueryPageParam;
import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.OpenCourseDto;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.delScore;
import com.example.academic_affairs_management_system.entity.Opencourse;
import com.example.academic_affairs_management_system.entity.Selectcourse;
import com.example.academic_affairs_management_system.entity.Teacher;
import com.example.academic_affairs_management_system.service.IOpencourseService;
import com.example.academic_affairs_management_system.service.ISelectcourseService;
import com.example.academic_affairs_management_system.service.ISemestatusService;
import com.example.academic_affairs_management_system.service.impl.OpencourseServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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


    @PostMapping("/insertNewCourse")
    public Result insertNewCourse(@RequestBody QueryPageParam queryPageParam){
        HashMap param = queryPageParam.getParam();
        String semester = param.get("semester").toString();
        System.out.println(semester);
        String courseId = param.get("courseId").toString();
        String staffId = param.get("staffId").toString();
        String classTime = param.get("classTime").toString();
        System.out.println(courseId);

        boolean success = iOpencourseService.insertNewCourse(semester,courseId,staffId,classTime);
        if(success){
            return Result.success();
        }
        return Result.fail("部分失败");

    }

    @GetMapping("/getNowSemCourse")
    public Result getNowSemCourse(@RequestParam String semester){
        List<OpenCourseDto> openCourseList = iOpencourseService.getNowSemCourse(semester);
        return Result.success(openCourseList,openCourseList.size());
    }

    @PostMapping("/updateOpenCou")
    public Result updateOpenCou(@RequestBody QueryPageParam queryPageParam){
        //修改开课表
        HashMap param = queryPageParam.getParam();
        String semester = param.get("semester").toString();
        String courseId = param.get("courseId").toString();
        String staffId = param.get("staffId").toString();
        String classTime = param.get("classTime").toString();
        int volume = Integer.parseInt(param.get("volume").toString());
        int remnant = Integer.parseInt(param.get("remnant").toString());

        boolean success = iOpencourseService.updateOpenCou(semester,courseId,staffId,classTime,volume,remnant);

        return Result.success();
    }

    @GetMapping("/getTeaClassTime")
    public Result getTeaClassTime(@RequestParam String semester,
                                  @RequestParam String staffId,
                                  @RequestParam String courseId){
        /*
        * 得到同一个老师在同一个学期下的所有开课时间
        * 传回给前端做对比*/
        List<Opencourse>  TeaClassTimeList = iOpencourseService.getTeaClassTime(semester,staffId,courseId);
        return Result.success(TeaClassTimeList,TeaClassTimeList.size());
    }
}
