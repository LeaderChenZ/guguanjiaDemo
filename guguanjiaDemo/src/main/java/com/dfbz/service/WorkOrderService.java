package com.dfbz.service;

import com.dfbz.entity.WorkOrder;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface WorkOrderService extends IService<WorkOrder> {
    PageInfo<WorkOrder> selectByCondition(Map<String, Object> params);

    WorkOrder selectById(Long id);
}
