package com.example.academic_affairs_management_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.academic_affairs_management_system.entity.Student;
import com.example.academic_affairs_management_system.entity.Teacher;
import com.example.academic_affairs_management_system.exception.ServiceException;
import com.example.academic_affairs_management_system.common.Constants;
import com.example.academic_affairs_management_system.entity.Login;
import com.example.academic_affairs_management_system.controller.dto.TeacherPack.TeacherDto;
import com.example.academic_affairs_management_system.controller.dto.StudentPack.StudentDto;
import com.example.academic_affairs_management_system.mapper.LoginMapper;
import com.example.academic_affairs_management_system.service.ILoginService;
import com.example.academic_affairs_management_system.utils.TokenUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import static org.apache.tomcat.util.buf.HexUtils.toHexString;


@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {

    @Autowired TeacherServiceImpl teacherService;
    @Autowired StudentServiceImpl studentService;

    @Autowired LoginMapper loginMapper;

    @Override
    public Map<String,Object> login(String username, String password) {
        Login login = getlogin(username,password);
        if(login!=null){
            Map<String,Object>res=new HashMap<>();
            if(login.getIdentify()==3){
                Student student = studentService.getstuInfo(username);
                StudentDto studentdto= new StudentDto();
                BeanUtils.copyProperties(student, studentdto);
                //设置token
                String token = TokenUtils.generateToken(login.getId(),login.getPassword());
                studentdto.setToken(token);
                res.put("data",studentdto);
                res.put("identify",login.getIdentify());
            }
            else{
                Teacher teacher = teacherService.getteacInfo(username);
                TeacherDto teacherdto= new TeacherDto();
                BeanUtils.copyProperties(teacher, teacherdto);
                //设置token
                String token = TokenUtils.generateToken(login.getId(),login.getPassword());
                teacherdto.setToken(token);
                res.put("data",teacherdto);
                res.put("identify",login.getIdentify());
            }
            return res;
        }else{
            throw new ServiceException(Constants.CODE_500,"用户名或密码错误");
        }
    }

    @Override
    public boolean changepw(String username, String password, String newpassword) {
        Login login = getlogin(username,password);
        System.out.println(login.getId());
        if(login!=null){

            return loginMapper.changepw(username,newpassword);
        }
        else{
            throw new ServiceException(Constants.CODE_500,"用户名或密码错误");
        }
    }

    @Override
    public Login getlogin(String username, String password)  {
        System.out.println(username+password);
        QueryWrapper<Login> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",username);
        String encodepass = "";
        if(!password.isEmpty()){
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("md5");
                md5.update(password.getBytes(StandardCharsets.UTF_8));
                byte[] md5Bytes = md5.digest();
                encodepass=toHexString(md5Bytes);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(encodepass);
            queryWrapper.eq("password",encodepass);
        }
        return getOne(queryWrapper);
    }


}
