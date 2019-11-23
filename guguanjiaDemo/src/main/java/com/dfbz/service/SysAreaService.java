package com.dfbz.service;

import com.dfbz.entity.SysArea;
import com.github.pagehelper.PageInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface SysAreaService extends IService<SysArea> {

    PageInfo<SysArea> selectByCondition(Map<String, Object> params);

    List<SysArea> selectByPid(long aid);

    OutputStream getListOutputStream(OutputStream outputStream);

    int importExcel(InputStream inputStream);

    SysArea selectByAreaId(long areaId);

    int updateArea(SysArea sysArea);
}
