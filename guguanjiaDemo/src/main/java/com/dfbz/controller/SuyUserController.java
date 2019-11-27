package com.dfbz.controller;

import com.dfbz.entity.SysUser;
import com.dfbz.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/27 16
 */
@RestController
@RequestMapping("manager/sysuser")
public class SuyUserController {
    @Autowired
    SysUserService service;

    @RequestMapping("list")
    public PageInfo<SysUser> list(@RequestBody Map<String, Object> params) {
        System.out.println(params);
        return service.selectByCondition(params);
    }
}
