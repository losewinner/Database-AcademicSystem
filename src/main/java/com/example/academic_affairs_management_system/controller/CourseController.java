package com.example.academic_affairs_management_system.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.academic_affairs_management_system.common.QueryPageParam;
import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.controller.dto.AdminPack.CourseDto;
import com.example.academic_affairs_management_system.entity.Course;
import com.example.academic_affairs_management_system.mapper.CourseMapper;
import com.example.academic_affairs_management_system.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("/course")
public class CourseController {
    @Autowired ICourseService iCourseService;
    @Resource
    private CourseMapper courseMapper;

    @GetMapping("/getratio")
    public Result getratio(@RequestParam String courseid){
        return Result.success(iCourseService.getratio(courseid));
    }

    @GetMapping("/getCourseDto")
    public Result getCourseDto(){
        List<CourseDto> courseList = iCourseService.getCourseDto();
        return Result.success(courseList,courseList.size());
    }

    @PostMapping("/updateCourse")
    public Result updateCourse(@RequestBody CourseDto course){
        /*
        *管理员修改课程 */
        //这里传回来的是CourseDto类型的，
        //但是下面用的Course是数据库表本身的对象的，所以不要搞混了
        System.out.println("管理员修改课程"+course);
        UpdateWrapper<Course> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("courseId",course.getCourseId())
                .set("credit",course.getCredit())
                .set("credit_hours",course.getCreditHours())
                .set("ratio",course.getRatio());
        boolean success = iCourseService.update(updateWrapper);
        if(success){
            return  Result.success();
        }
        else{
            return Result.fail("修改失败");
        }
    }

    @PostMapping("/delCourse")
    public Result delCourse(@RequestBody QueryPageParam queryPageParam){
        HashMap param = queryPageParam.getParam();
        System.out.println("删除课程列表"+param.get("DeleteList").getClass());
        List<LinkedHashMap<String,Object>> deleteList = (List<LinkedHashMap<String, Object>>) param.get("DeleteList");

        for(LinkedHashMap<String,Object>map:deleteList){
            String delEle = map.get("courseId").toString();
            courseMapper.deleteById(delEle);
        }
        return Result.success();
    }
}
