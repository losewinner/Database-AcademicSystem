package com.example.academic_affairs_management_system.service.impl;

import com.example.academic_affairs_management_system.entity.Selectcourse;
import com.example.academic_affairs_management_system.mapper.SelectcourseMapper;
import com.example.academic_affairs_management_system.service.ISelectcourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
@Service
public class SelectcourseServiceImpl extends ServiceImpl<SelectcourseMapper, Selectcourse> implements ISelectcourseService {

    @Resource
    private SelectcourseMapper selectcourseMapper;
    @Override
    public List<Selectcourse> getAllInfo(String semester,String courseId,String staffId,String classTime){
        return selectcourseMapper.getAllInfo(semester,courseId,staffId,classTime);
    }
    @Override
    public List<Selectcourse> selectAll(){
        return selectcourseMapper.selectAll();
    }

    @Override
    public List<Selectcourse> getCourseScore(String semester,String courseId,String courseName){
        return selectcourseMapper.getCourseScore(semester,courseId,courseName);
    }
}
