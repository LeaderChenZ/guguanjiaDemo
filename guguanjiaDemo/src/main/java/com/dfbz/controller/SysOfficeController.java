package com.dfbz.controller;

import com.dfbz.entity.SysOffice;
import com.dfbz.service.SysOfficeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/15 20
 */
@RestController
@RequestMapping("manager/office")
public class SysOfficeController {

    @Autowired
    SysOfficeService service;

    @RequestMapping("list")
    public List<SysOffice> list() {
        return service.selectAll();
    }

    @RequestMapping("")
    public PageInfo<SysOffice> index(@RequestBody Map<String,Object> params){
        return service.selectByCondition(params);
    }
}
