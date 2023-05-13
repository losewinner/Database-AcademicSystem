package com.example.academic_affairs_management_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.entity.Semestatus;
import com.example.academic_affairs_management_system.service.ISemestatusService;
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
@RequestMapping("/semestatus")
public class SemestatusController {
    @Autowired
    private ISemestatusService iSemestatusService;

    @GetMapping("/list")
    public List<Semestatus> list(){
        return iSemestatusService.list();
    }
}
