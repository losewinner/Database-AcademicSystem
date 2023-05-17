package com.example.academic_affairs_management_system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.academic_affairs_management_system.entity.Login;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LoginMapper extends BaseMapper<Login> {


    @Update("Update login " +
            "set password= MD5(#{newpassword}) " +
            "where id=#{username} ")
    boolean changepw(String username, String newpassword);
}
