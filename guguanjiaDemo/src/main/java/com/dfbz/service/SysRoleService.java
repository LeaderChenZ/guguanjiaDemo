package com.dfbz.service;

import com.dfbz.entity.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SysRoleService extends IService<SysRole>{
    PageInfo<SysRole> selectByCondition(Map<String, Object> params);

    int updateByUids(long rid, long... uids);

    int  insertBath(List<Long> cids, long rid);
}
