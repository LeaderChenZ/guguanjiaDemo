package com.dfbz.dao;

import com.dfbz.entity.SysResource;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysResourceMapper extends Mapper<SysResource> {
    
    @Select("select  " +
            "se.* " +
            "from  " +
            "sys_role sr,sys_role_resource so,sys_resource se " +
            "where " +
            "so.role_id = sr.id " +
            "and " +
            "so.resource_id = se.id " +
            "and se.type=0 " +
            "and " +
            "sr.id = #{rid}")
    List<SysResource> selectByRid(long rid); //查询角色的权限（左边侧栏的数据）
}