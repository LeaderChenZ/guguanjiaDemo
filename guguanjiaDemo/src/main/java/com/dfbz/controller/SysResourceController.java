package com.dfbz.controller;

import com.dfbz.entity.SysResource;
import com.dfbz.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chen
 * @description
 * @date 2019/11/30 15
 */
@RestController
@RequestMapping("manager/menu")
public class SysResourceController {

    @Autowired
    SysResourceService service;

    @RequestMapping("selectAllByUid")
    public List<SysResource> selectAllByUid(long uid){
        return service.selectAllByUid(uid);
    }
}
