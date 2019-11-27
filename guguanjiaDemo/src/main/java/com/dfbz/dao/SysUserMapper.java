package com.dfbz.dao;

import com.dfbz.entity.SysUser;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
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
}