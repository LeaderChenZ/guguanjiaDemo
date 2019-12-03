package com.dfbz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("manager/syslog")
@RestController
public class SysLogController {


    @RequestMapping("")
    public ModelAndView toIndex(){
        return new ModelAndView("/html/log/log.html");
    }
}
