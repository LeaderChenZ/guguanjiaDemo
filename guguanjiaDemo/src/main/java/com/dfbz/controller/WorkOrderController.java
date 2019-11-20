package com.dfbz.controller;

import com.dfbz.entity.WorkOrder;
import com.dfbz.service.WorkOrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/19 18
 */
@RestController
@RequestMapping("manager/admin")
public class WorkOrderController {
    @Autowired
    WorkOrderService service;

    @RequestMapping("work")
    public PageInfo<WorkOrder> index(@RequestBody Map<String, Object> params) {
        return service.selectByCondition(params);
    }

    @RequestMapping("work/detail")
    public WorkOrder selectById(Long id) {
        return service.selectById(id);
    }
}
