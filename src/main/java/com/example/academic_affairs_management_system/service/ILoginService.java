package com.example.academic_affairs_management_system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.academic_affairs_management_system.entity.Login;

import java.security.NoSuchAlgorithmException;

public interface ILoginService extends IService<Login> {

    Login getlogin(String username, String password) throws NoSuchAlgorithmException;

    Object login(String username, String password);

    boolean changepw(String username, String password, String newpassword);
}
