package com.dfbz.controller;

import com.dfbz.entity.AppVersion;
import com.dfbz.entity.Result;
import com.dfbz.service.AppVersionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * @author Chen
 * @description
 * @date 2019/11/12 18
 */
@Controller
@RequestMapping("manager/app")
public class AppVersionController {
    @Autowired
    AppVersionService service;


    @RequestMapping("index")
    @ResponseBody
    //@RequestParam(defaultValue = "1")设置参数默认值
    public PageInfo<AppVersion> index(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        return service.selectAll(pageNum, pageSize);
    }

    @RequestMapping("toUpdate")
    @ResponseBody
    public AppVersion toUpdate(Long id) {
        return service.selectByPrimaryKey(id);
    }

    @RequestMapping("update")
    @ResponseBody
    public Result update(@RequestBody AppVersion appVersion) {
        int i = service.updateByPrimaryKeySelective(appVersion);
        Result result = new Result();
        if (i > 0) {
            result.setMsg("操作成功！");
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping("doDelete")
    @ResponseBody
    public Result doDelete(Long id) {
        AppVersion appVersion = new AppVersion();
        appVersion.setId(id);
        appVersion.setDelFlag("1");
        appVersion.setUpdateDate(new Date());
        //逻辑删除  动态更新
        int i = service.delete(appVersion);
        Result result = new Result();
        if (i > 0) {
            result.setMsg("操作成功！");
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping("doInsert")
    @ResponseBody
    public Result doInsert(@RequestBody AppVersion appVersion){

        appVersion.setDelFlag("0");
        appVersion.setUpdateDate(new Date());
        appVersion.setCreateDate(new Date());
        appVersion.setCreateBy("2,超级管理员");
        int insert = service.insert(appVersion);
        Result result = new Result();
        if (insert>0){
            result.setMsg("添加成功！");
            result.setSuccess(true);
        }
        return result;
    }
}
