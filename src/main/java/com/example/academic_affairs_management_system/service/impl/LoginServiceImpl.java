package com.example.academic_affairs_management_system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.academic_affairs_management_system.entity.Login;
import com.example.academic_affairs_management_system.mapper.LoginMapper;
import com.example.academic_affairs_management_system.service.ILoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements ILoginService {

}
