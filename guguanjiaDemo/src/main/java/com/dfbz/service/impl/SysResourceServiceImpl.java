package com.dfbz.service.impl;

import com.dfbz.dao.SysResourceMapper;
import com.dfbz.entity.SysResource;
import com.dfbz.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    @Override
    public List<SysResource> selectByRid(long rid) {
        return mapper.selectByRid(rid);
    }
}
