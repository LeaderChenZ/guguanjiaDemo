package com.dfbz.service.impl;

import com.dfbz.dao.SysRoleMapper;
import com.dfbz.entity.SysRole;
import com.dfbz.service.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/27 16
 */
@Service
@Transactional
public class SysRoleServiceImpl extends IServiceImpl<SysRole> implements SysRoleService {

    @Autowired
    SysRoleMapper roleMapper;

    @Override
    public PageInfo<SysRole> selectByCondition(Map<String, Object> params) {
        //默认值设置
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));
        List<SysRole> sysRole = roleMapper.selectByCondition(params);
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRole);//生成分页对象

        return pageInfo;
    }

    @Override
    public int updateByUids(long rid, long... uids) {
        return roleMapper.updateByUids(rid, uids);
    }

    @Override
    public int insertBath(List<Long> cids, long rid) {
        return roleMapper.insertBath(cids, rid);
    }

}
