package com.dfbz.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.dfbz.dao.SysAreaMapper;
import com.dfbz.entity.SysArea;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen
 * @description Area监听器
 * @date 2019/11/21 21
 */
public class SysAreaListener extends AnalysisEventListener<SysArea> {

    private SysAreaMapper mapper;
    private List<SysArea> sysAreas = new ArrayList<>();

    public SysAreaListener() {
    }

    public SysAreaListener(SysAreaMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void invoke(SysArea sysArea, AnalysisContext analysisContext) {
        System.out.println("读取到数据" + sysArea);
        sysAreas.add(sysArea);
        if (sysAreas.size() > 5) {
            mapper.insertBatch(sysAreas);
            sysAreas.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读取完成");
        if (sysAreas.size() > 0) {
            mapper.insertBatch(sysAreas);
        }
    }
}
