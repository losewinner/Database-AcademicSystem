package com.example.academic_affairs_management_system.utils;


import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.academic_affairs_management_system.entity.Teacher;
import com.example.academic_affairs_management_system.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.auth0.jwt.JWT;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenUtils {
    private static final long EXPIRE_TIME = 120 * 60 * 1000; //120分钟(2小时)

    public static String generateToken(String userId,String password){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        String token="";
        token= JWT.create().withAudience(userId) // 将 user id 保存到 token 里面
                .withExpiresAt(date) //2小时后token过期
                .sign(Algorithm.HMAC256(password)); // 以 password 作为 token 的密钥
        return token;

    }
}
