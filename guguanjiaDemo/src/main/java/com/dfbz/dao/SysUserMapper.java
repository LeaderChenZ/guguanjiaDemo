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


    //根据公司id，角色id 查询出当前选中公司的未给当前角色授权的用户
    @Select("select * " +
            "from  " +
            "sys_user " +
            "where " +
            "office_id = 56 " +
            "and " +
            "id not in  " +
            "(select sur.user_id from " +
            "sys_role sr ,sys_user_role sur  " +
            "where " +
            "sr.id=1 " +
            "and " +
            "sr.id = sur.role_id " +
            ")")
    List<SysUser> selectNoRole(@Param("rid") long rid,@Param("oid")long oid);
}