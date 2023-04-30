package com.example.academic_affairs_management_system.service;

import com.example.academic_affairs_management_system.entity.Selectcourse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
public interface ISelectcourseService extends IService<Selectcourse> {
    public List<Selectcourse> getAllInfo(String semester, String courseId, String staffId, String classTime) ;

}
