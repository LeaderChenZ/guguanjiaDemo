package com.dfbz.controller;

import com.dfbz.entity.Statute;
import com.dfbz.service.StatuteService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/20 20
 */
@RestController
@RequestMapping("manager/statute")
public class StatuteController {
    @Autowired
    StatuteService service;

    @RequestMapping("index")
    public PageInfo<Statute> index(Map<String, Object> params) {
        Object pageNum = params.get("pageNum");
        System.out.println(pageNum);
        return service.selectByCondition(params);

    }

}
