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
        try{
            return Result.success(iLoginService.login(username,password));
        }catch (Exception e){
            return Result.fail("用户名或者密码错误");
        }
    }
    @GetMapping("/changepw")
    public Result changepw(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String newpassword){
        try{
            iLoginService.changepw(username,password,newpassword);
            return Result.success("修改成功");
        }catch (Exception e){
            return Result.fail("原密码错误");
        }
    }

}
