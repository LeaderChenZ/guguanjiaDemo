package com.dfbz.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dfbz.dao.SysAreaMapper;
import com.dfbz.entity.SysArea;
import com.dfbz.listener.SysAreaListener;
import com.dfbz.service.SysAreaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/21 19
 */
@Service
@Transactional
public class SysAreaServiceImpl extends IServiceImpl<SysArea> implements SysAreaService {
    @Autowired
    SysAreaMapper areaMapper;


    //分页
    @Override
    public PageInfo<SysArea> selectByCondition(Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));
        if (params.containsKey("name") && !StringUtils.isEmpty(params.get("name"))) {
            params.put("name", params.get("name"));
        }
        List<SysArea> list = areaMapper.selectByCondition(params);
        PageInfo<SysArea> pageInfo = new PageInfo<>(list);
        return pageInfo;


    }

    @Override
    public List<SysArea> selectByPid(long aid) {
        return areaMapper.selectByPid(aid);
    }


    /*
    根据excel工具转变成输出流
    *
    * */
    @Override
    public OutputStream getListOutputStream(OutputStream outputStream) {
        List<SysArea> list = areaMapper.selectAll();

        /*
         * 1.构建excel对象，传入写入文件和每行记录相对应的java类字节文件对象
         * 如果需要自定义设置写入excel中的表头数据或数据格式，需要通过easyExcel的属性设置
         * */

        ExcelWriter excelWriter = EasyExcel.write(outputStream, SysArea.class).build();
        //2.操作excel对象，用于设置excel的设置
        WriteSheet writeSheet = EasyExcel.writerSheet(0).build();
        //3.写出
        excelWriter.write(list, writeSheet);
        return outputStream;
    }


    /*
     *写入对象
     * */
    @Override
    public int importExcel(InputStream inputStream) {
        int result = 0;
        ExcelReader excelReader = EasyExcel.read(inputStream, SysArea.class, new SysAreaListener(areaMapper)).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        excelReader.finish();
        result++;
        return result;
    }


    /*
     * 查询sys_area id对应的数据
     * */
    @Override
    public SysArea selectByAreaId(long areaId) {
        return areaMapper.selectByAreaId(areaId);
    }


    @Override
    public int updateArea(SysArea sysArea) {
        //1.更新当前区域数据
        int result = areaMapper.updateByPrimaryKey(sysArea);

        //2.根据oldParents和parentIds判断是否有更新该parentIds字段，
        if (!sysArea.getOldParentIds().equals(sysArea.getParentIds())) {
            areaMapper.updateParentIds(sysArea);
            result++;
        }
        return result;
    }
}
