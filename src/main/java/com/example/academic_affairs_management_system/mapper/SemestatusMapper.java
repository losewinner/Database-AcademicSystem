package com.example.academic_affairs_management_system.mapper;

import com.example.academic_affairs_management_system.entity.Semestatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author losewinner
 * @since 2023-04-27
 */
@Mapper
public interface SemestatusMapper extends BaseMapper<Semestatus> {

    @Select("select max(semester) from semestatus where status = 2")
    public String getnowsemester();
}
