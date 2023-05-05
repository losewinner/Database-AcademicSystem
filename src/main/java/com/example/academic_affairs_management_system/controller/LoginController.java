package com.example.academic_affairs_management_system.controller;

import com.example.academic_affairs_management_system.common.Constants;
import com.example.academic_affairs_management_system.common.Result;
import com.example.academic_affairs_management_system.service.ILoginService;
import com.example.academic_affairs_management_system.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    ILoginService iLoginService;

    @GetMapping
    public Result login(@RequestParam String username,
                        @RequestParam String password){
        //校验
        if(username.isEmpty()||password.isEmpty()){
            return Result.fail("参数错误");
        }
        if(username.length()==8){
            return Result.success(iLoginService.loginstu(username,password));
        }
        return Result.success(iLoginService.logintea(username,password));
    }
}
