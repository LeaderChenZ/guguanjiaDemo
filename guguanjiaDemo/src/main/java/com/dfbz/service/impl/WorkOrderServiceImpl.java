package com.dfbz.service.impl;

import com.dfbz.dao.DetailMapper;
import com.dfbz.dao.TransferMapper;
import com.dfbz.dao.WorkOrderMapper;
import com.dfbz.entity.Detail;
import com.dfbz.entity.Transfer;
import com.dfbz.entity.WorkOrder;
import com.dfbz.service.WorkOrderService;
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
 * @date 2019/11/19 18
 */
@Service
@Transactional
public class WorkOrderServiceImpl extends IServiceImpl<WorkOrder> implements WorkOrderService {

    @Autowired
    DetailMapper detailMapper;
    @Autowired
    TransferMapper transferMapper;

    @Override
    public PageInfo<WorkOrder> selectByCondition(Map<String, Object> params) {
        //默认值设置
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }
        //开启分页
        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));
        WorkOrderMapper workOrderMapper = (WorkOrderMapper) mapper;
        List<WorkOrder> workOrders = workOrderMapper.selectByCondition(params);
        PageInfo<WorkOrder> pageInfo = new PageInfo<>(workOrders);

        return pageInfo;
    }

    /*
     * 根据联单的id查询联单详情
     * 1.worder信息
     * 2.根据order的id查询Detail
     * 3.根据order的id查询转运记录Transfer
     *
     * */
    @Override
    public WorkOrder selectById(Long id) {
        WorkOrderMapper workOrderMapper = (WorkOrderMapper) mapper;
        WorkOrder workOrder = workOrderMapper.selectById(id);
        List<Detail> details = detailMapper.selectById(id);
        workOrder.setDetails(details);
        List<Transfer> transfers = transferMapper.selectById(id);
        workOrder.setTransfers(transfers);
        return workOrder;
    }
}
