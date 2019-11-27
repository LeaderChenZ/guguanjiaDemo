package com.dfbz.service.impl;

import com.dfbz.dao.SysUserMapper;
import com.dfbz.entity.SysUser;
import com.dfbz.service.SysUserService;
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
public class SysUserServiceImpl extends IServiceImpl<SysUser> implements SysUserService {

    @Autowired
    SysUserMapper userMapper;
    @Override
    public PageInfo<SysUser> selectByCondition(Map<String, Object> params) {
        //默认值设置
        if(StringUtils.isEmpty(params.get("pageNum"))){
            params.put("pageNum",1);
        }
        if(StringUtils.isEmpty(params.get("pageSize"))){
            params.put("pageSize",5);
        }
        PageHelper.startPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));
        SysUserMapper sysUserMapper= (SysUserMapper) mapper;
        List<SysUser> sysUsers = sysUserMapper.selectByCondition(params);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);//生成分页对象

        return pageInfo;
    }


}
