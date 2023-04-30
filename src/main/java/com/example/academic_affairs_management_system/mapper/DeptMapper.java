package com.example.academic_affairs_management_system.mapper;

import com.example.academic_affairs_management_system.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.academic_affairs_management_system.entity.Selectcourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
    List<Selectcourse> getAllInfo();
}
