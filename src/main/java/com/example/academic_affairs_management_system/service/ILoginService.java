package com.example.academic_affairs_management_system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.academic_affairs_management_system.entity.Login;

public interface ILoginService extends IService<Login> {

    Object loginstu(String username, String password);

    Login getlogin(String username, String password);

    Object logintea(String username, String password);
}
