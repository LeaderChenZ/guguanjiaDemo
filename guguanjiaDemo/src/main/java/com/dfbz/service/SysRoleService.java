package com.dfbz.service;

import com.dfbz.entity.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface SysRoleService extends IService<SysRole>{
    PageInfo<SysRole> selectByCondition(Map<String, Object> params);

    int updateByUids(long rid, long... uids);
}
