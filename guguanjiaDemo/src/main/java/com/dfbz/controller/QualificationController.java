package com.dfbz.controller;

import com.dfbz.entity.Qualification;
import com.dfbz.service.QualificationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/14 20
 */
@RestController
@RequestMapping("manager/qualification")
public class QualificationController  {

    @Autowired
    QualificationService service;
    @RequestMapping("index")
    public PageInfo<Qualification> index(@RequestBody Map<String,Object> params){
          return   service.selectByCondition(params);
    }
}
