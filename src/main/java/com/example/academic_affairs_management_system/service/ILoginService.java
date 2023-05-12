package com.example.academic_affairs_management_system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.academic_affairs_management_system.entity.Login;

import java.security.NoSuchAlgorithmException;

public interface ILoginService extends IService<Login> {

    Object loginstu(String username, String password);

    Login getlogin(String username, String password) throws NoSuchAlgorithmException;

    Object logintea(String username, String password);
}
