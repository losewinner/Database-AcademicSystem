package com.example.academic_affairs_management_system.service.impl;

import com.example.academic_affairs_management_system.entity.Semestatus;
import com.example.academic_affairs_management_system.mapper.SemestatusMapper;
import com.example.academic_affairs_management_system.service.ISemestatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
@Service
public class SemestatusServiceImpl extends ServiceImpl<SemestatusMapper, Semestatus> implements ISemestatusService {

    @Autowired SemestatusMapper semestatusMapper;
    @Override
    public String getnowsemester() {
        return semestatusMapper.getnowsemester();
    }

    @Override
    public boolean addSemester(String semester,int status){
        return semestatusMapper.addSemester(semester,status);
    }

    @Override
    public boolean setStatus(String semester,int status){
        return semestatusMapper.setStatus(semester,status);
    }
}
