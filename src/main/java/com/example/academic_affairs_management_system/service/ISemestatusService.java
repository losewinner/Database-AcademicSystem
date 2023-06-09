package com.example.academic_affairs_management_system.service;

import com.example.academic_affairs_management_system.entity.Semestatus;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
public interface ISemestatusService extends IService<Semestatus> {
    public String getnowsemester();

    public boolean addSemester(String semester,int status);

    public boolean setnewStatus(String semester,int status);
}
