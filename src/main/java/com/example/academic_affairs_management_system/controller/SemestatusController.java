package com.example.academic_affairs_management_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.entity.Semestatus;
import com.example.academic_affairs_management_system.service.ISemestatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/semestatus")
public class SemestatusController {
    @Autowired
    private ISemestatusService iSemestatusService;

    @GetMapping("/list")
    public List<Semestatus> list(){
        return iSemestatusService.list();
    }

    @GetMapping("setStatus")
    public Result setStatus(@RequestParam String semester,
                            @RequestParam int status){
        UpdateWrapper<Semestatus> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("semester",semester).set("status",status);
        boolean success = iSemestatusService.update(updateWrapper);
        if(success){
            return Result.success();
        }
        else{
            return Result.fail("学期状态修改失败");
        }
    }
}
