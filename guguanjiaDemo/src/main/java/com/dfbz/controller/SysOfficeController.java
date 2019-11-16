package com.dfbz.controller;

import com.dfbz.entity.SysOffice;
import com.dfbz.service.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
