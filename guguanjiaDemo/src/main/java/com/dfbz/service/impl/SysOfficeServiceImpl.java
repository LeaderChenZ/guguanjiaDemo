package com.dfbz.service.impl;

import com.dfbz.dao.SysOfficeMapper;
import com.dfbz.entity.SysOffice;
import com.dfbz.service.SysOfficeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description 办公室
 * @date 2019/11/15 19
 */
@Service
@Transactional
@CacheConfig(cacheNames = "officeCache")//设置全局缓存设置
public class SysOfficeServiceImpl extends IServiceImpl<SysOffice> implements SysOfficeService {
    @Override
    public PageInfo<SysOffice> selectByCondition(Map<String, Object> params) {
        //默认值设置
        if(StringUtils.isEmpty(params.get("pageNum"))){
            params.put("pageNum",1);
        }
        if(StringUtils.isEmpty(params.get("pageSize"))){
            params.put("pageSize",5);
        }
        PageHelper.startPage((Integer) params.get("pageNum"),(Integer) params.get("pageSize"));
        SysOfficeMapper sysOfficeMapper= (SysOfficeMapper) mapper;
        List<SysOffice> sysOffices = sysOfficeMapper.selectByCondition(params);

        PageInfo<SysOffice> pageInfo = new PageInfo<>(sysOffices);//生成分页对象

        return pageInfo;
    }

    @Cacheable(key = "'SysOfficeServcieImpl:selectByOid:'+#oid")
    @Override
    public SysOffice selectByOid(long oid){
        SysOfficeMapper sysOfficeMapper= (SysOfficeMapper) mapper;
        return sysOfficeMapper.selectByOid(oid);
    }

    @Cacheable(key = "'SysOfficeServiceImpl:selectAll'")
    @Override
    public List<SysOffice> selectAll(){
        return super.selectAll();
    }
}
