package com.dfbz.dao;

import com.dfbz.entity.SysArea;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysAreaMapper extends Mapper<SysArea> {
    @SelectProvider(type = SysAreaProvider.class,method = "selectByCondition")
    List<SysArea> selectByCondition(Map<String,Object> condition);



    //模糊查询所有的父区域
    @Select("select sub.*,parent.name parentName " +
            "from " +
            " sys_area sub,sys_area parent " +
            "where " +
            " sub.parent_ids like CONCAT('%',#{aid},'%') " +
            "and " +
            " sub.parent_id=parent.id")
    List<SysArea> selectByPid(long aid);


    //动态添加
    @InsertProvider(type = SysAreaProvider.class,method = "insertBatch")
    int insertBatch(@Param("sysAreas") List<SysArea> sysAreas);
    
    
    @Select("select sa.*,su.`name` parentName " +
            "from " +
            "sys_area sa  " +
            "LEFT JOIN " +
            "sys_area su " +
            "on " +
            "sa.parent_id = su.id " +
            "where  " +
            "sa.id = ${areaId}")
    SysArea selectByAreaId(long areaId);


    /**
     * 根据当前区域的id更新所有其子级区域的parentIds
     * @return
     */
    @Update("update  " +
            " sys_area " +
            "set " +
            " parent_ids = REPLACE(parent_ids,#{oldParentIds},#{parentIds}) " +
            "where " +
            " parent_ids like concat('%',#{id},'%')")
    int updateParentIds(SysArea sysArea);



}