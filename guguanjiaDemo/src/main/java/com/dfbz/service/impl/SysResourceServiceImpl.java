package com.dfbz.service.impl;

import com.dfbz.dao.SysResourceMapper;
import com.dfbz.dao.SysRoleMapper;
import com.dfbz.entity.SysResource;
import com.dfbz.entity.SysRole;
import com.dfbz.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Chen
 * @description
 * @date 2019/11/27 16
 */
@Service
@Transactional
public class SysResourceServiceImpl extends IServiceImpl<SysResource> implements SysResourceService {

    @Autowired
    SysResourceMapper mapper;

    @Autowired
    SysRoleMapper roleMapper;


    @Override
    public List<SysResource> selectByRid(long rid) {
        return mapper.selectByRid(rid);
    }

    @Override
    public List<SysResource> selectAllByUid(long uid) {
        //查询一个用户有多少个角色
        List<SysRole> sysRoles = roleMapper.selectRoleByUid(uid);
        List<SysResource> userResources = null;
        if (sysRoles != null) {
            //遍历所有角色，查询每个角色的权限，   去重
            Set<SysResource> set = new HashSet<>();
            for (SysRole sysRole : sysRoles) {
                List<SysResource> sysResources = mapper.selectByRid(sysRole.getId()); //查询每个角色的权限
                set.addAll(sysResources);//去重
            }
            userResources = new ArrayList<>();
            userResources.addAll(set);
        }
        return userResources;
    }
}
