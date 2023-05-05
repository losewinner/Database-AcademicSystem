package com.example.academic_affairs_management_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.academic_affairs_management_system.entity.Teacher;
import com.example.academic_affairs_management_system.exception.ServiceException;
import com.example.academic_affairs_management_system.common.Constants;
import com.example.academic_affairs_management_system.controller.dto.TeacherPack.Student;
import com.example.academic_affairs_management_system.entity.Login;
import com.example.academic_affairs_management_system.controller.dto.TeacherPack.TeacherDto;
import com.example.academic_affairs_management_system.mapper.LoginMapper;
import com.example.academic_affairs_management_system.service.ILoginService;
import com.example.academic_affairs_management_system.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {

    @Autowired TeacherServiceImpl teacherService;
    @Override
    public Student loginstu(String username, String password) {
        return null;
    }


    @Override
    public Map<String,Object> logintea(String username, String password) {
        Login login = getlogin(username,password);
        if(login!=null){
            Map<String,Object>res=new HashMap<>();
            Teacher teacher = teacherService.getteacInfo(username);
            TeacherDto teacherdto= new TeacherDto();
            BeanUtils.copyProperties(teacher, teacherdto);
            //设置token
            String token = TokenUtils.generateToken(login.getId(),login.getPassword());
            teacherdto.setToken(token);
            res.put("data",teacherdto);
            res.put("identify",login.getIdentify());
            return res;
        }else{
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }

    }

    @Override
    public Login getlogin(String username, String password){
        QueryWrapper<Login> queryWrapper = new QueryWrapper<Login>();
        queryWrapper.eq("id",username);
        if(!password.isEmpty()){
            queryWrapper.eq("password",password);
        }
        try{
            return getOne(queryWrapper);
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(Constants.CODE_500,"用户名或者密码错误");
        }
    }


}
