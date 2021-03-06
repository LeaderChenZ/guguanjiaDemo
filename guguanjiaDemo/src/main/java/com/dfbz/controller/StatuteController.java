package com.dfbz.controller;

import com.dfbz.entity.Statute;
import com.dfbz.service.StatuteService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("")
    public ModelAndView toIndex() {
        return new ModelAndView("/html/statute/index.html");
    }

    @RequestMapping("index")
    public PageInfo<Statute> index(@RequestBody Map<String, Object> params) {
        System.out.println(params);
       /* Map<String,Object> map = new HashMap<>();
        map.put("pageNum",pageNum);+
        map.put("pageSize",pageSize);*/
        return service.selectByCondition(params);

    }

}
