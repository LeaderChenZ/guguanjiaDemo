package com.dfbz.dao;

import com.dfbz.entity.SysUser;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends Mapper<SysUser> {
    @SelectProvider(type = SysUserProvider.class, method = "selectByCondition")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(property = "roles",column = "id",many = @Many(select = "com.dfbz.dao.SysRoleMapper.selectRoleByUid"))
    })
    List<SysUser> selectByCondition(Map<String, Object> params);


    @Select("select su.* " +
            "from  " +
            "sys_role sr  , sys_user_role sur , sys_user su  " +
            "where  " +
            "sur.user_id = su.id  " +
            "and  " +
            "sur.role_id = sr.id  " +
            "and  " +
            "su.del_flag=0  " +
            "and  " +
            "sr.id =#{rid} ")
    List<SysUser> selectByRid(long rid);
}