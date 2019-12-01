package com.dfbz.dao;

import com.dfbz.entity.SysRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysRoleMapper extends Mapper<SysRole> {
    @Select("select sr.*   " +
            "from   " +
            "sys_user su   " +
            "left join   " +
            "sys_user_role sur    " +
            "on    " +
            "sur.user_id = su.id   " +
            "left join   " +
            "sys_role sr    " +
            "on   " +
            "sur.role_id = sr.id   " +
            "where   " +
            "su.id = #{uid}")
    List<SysRole> selectRoleByUid(long uid);  //查询一个用户有多少个角色

    @SelectProvider(type = SysRoleProvider.class, method = "selectByCondition")
    List<SysRole> selectByCondition(Map<String, Object> params); //动态查询（分页查询，条件查询）

    @UpdateProvider(type = SysRoleProvider.class, method = "updateByUids")
    int updateByUids(@Param("rid") long rid, @Param("uids") long... uids);
}