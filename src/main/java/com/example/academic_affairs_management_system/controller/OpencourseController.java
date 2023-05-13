package com.example.academic_affairs_management_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.academic_affairs_management_system.common.QueryPageParam;
import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.delScore;
import com.example.academic_affairs_management_system.entity.Opencourse;
import com.example.academic_affairs_management_system.entity.Selectcourse;
import com.example.academic_affairs_management_system.entity.Teacher;
import com.example.academic_affairs_management_system.service.IOpencourseService;
import com.example.academic_affairs_management_system.service.ISelectcourseService;
import com.example.academic_affairs_management_system.service.ISemestatusService;
import com.example.academic_affairs_management_system.service.impl.OpencourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
        String courseId = param.get("courseId").toString();
        String classTime = param.get("classTime").toString();
        //把前端传回来的param里面的List类型的数据转换成为List<Teacher>类型的
        List<LinkedHashMap<String, Object>> teacherlist = (List<LinkedHashMap<String, Object>>) param.get("teacherlist");
        //一个一个插入
        List<Opencourse> openCouList= new ArrayList<>();
        for(LinkedHashMap<String,Object>map:teacherlist) {
            Opencourse newcourse = new Opencourse();
            newcourse.setSemester(semester);
            newcourse.setCourseid(courseId);
            newcourse.setClasstime(classTime);
            newcourse.setStaffid(map.get("staffId").toString());
            openCouList.add(newcourse);
        }
        boolean success = iOpencourseService.saveBatch(openCouList);
        if(success){
            return Result.success();
        }
        return Result.fail("添加进开课表失败");
    }
}
