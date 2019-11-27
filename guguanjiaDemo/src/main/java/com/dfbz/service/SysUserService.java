package com.dfbz.service;

import com.dfbz.entity.SysUser;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface SysUserService extends IService<SysUser>{
    PageInfo<SysUser> selectByCondition(Map<String, Object> params);


}
