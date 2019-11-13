package com.dfbz.service.impl;

import com.dfbz.entity.AppVersion;
import com.dfbz.service.AppVersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chen
 * @description
 * @date 2019/11/12 18
 */
@Service
@Transactional
public class AppVersionServiceImpl extends IServiceImpl<AppVersion> implements AppVersionService {

    @Override
    public PageInfo<AppVersion> selectAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);//开启分页拦截功能


        List<AppVersion> appVersions = mapper.selectAll();//自动分页
        PageInfo<AppVersion> pageInfo = new PageInfo<>(appVersions);//生成分页对象
        return pageInfo;
    }
}
