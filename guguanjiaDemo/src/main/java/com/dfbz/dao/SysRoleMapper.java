package com.dfbz.dao;

import com.dfbz.entity.SysRole;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

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
    List<SysRole> selectRoleByUid(long uid);
}