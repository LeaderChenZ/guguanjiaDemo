package com.dfbz.controller;

import com.dfbz.entity.Qualification;
import com.dfbz.entity.Result;
import com.dfbz.service.QualificationService;
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
 * @date 2019/11/14 20
 */
@RestController
@RequestMapping("manager/qualification")
public class QualificationController {

    @Autowired
    QualificationService service;

    @RequestMapping("")
    public ModelAndView toIndex(){
        return new ModelAndView("/html/qualification/index.html");
    }

    @RequestMapping("index")
    public PageInfo<Qualification> index(@RequestBody Map<String, Object> params) {
        return service.selectByCondition(params);
    }

    @RequestMapping("toUpdate")
    public Qualification toUpdate(Long id) {
        return service.selectByPrimaryKey(id);
    }

    @RequestMapping("update")
    public Result update(@RequestBody Qualification qualification){
        //不更新address  更新审核状态、审核人
        qualification.setAddress(null);
        int i = service.updateByPrimaryKeySelective(qualification);
        Result result = new Result();
        if (i>0){
            result.setMsg("操作成功");
            result.setSuccess(true);
        }
        return result;
    }
}
