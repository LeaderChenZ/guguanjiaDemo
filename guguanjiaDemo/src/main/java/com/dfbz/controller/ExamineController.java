package com.dfbz.controller;

import com.dfbz.entity.Examine;
import com.dfbz.service.ExamineService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/15 19
 */
@RestController
@RequestMapping("manager/examine")
public class ExamineController {

    @Autowired
    ExamineService service;


    //查询分页
    @RequestMapping("index")
    public PageInfo<Examine> index(@RequestBody Map<String, Object> params) {
        return service.selectAll(params);
    }

    @RequestMapping("toUpdate")
    public Examine toUpdate(Long id) {
        return service.selectByPrimaryKey(id);
    }
}
