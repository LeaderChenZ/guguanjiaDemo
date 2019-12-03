package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.SysUser;
import com.dfbz.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
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
    @RequestMapping("")
    public ModelAndView toIndex(){
        return new ModelAndView("/html/user/user.html");
    }


    @RequestMapping("list")
    public PageInfo<SysUser> list(@RequestBody Map<String, Object> params) {

        return service.selectByCondition(params);
    }

    @RequestMapping("toDelete")
    public Result toDelete(long id) {
        SysUser user = new SysUser();
        user.setId(id);
        user.setDelFlag("1");
        user.setUpdateDate(new Date());
        int i = service.delete(user);
        Result result = new Result();
        if (i > 0) {
            result.setMsg("操作成功！");
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping("selectByRid")
    public List<SysUser> selectByRid(long rid) {
        return service.selectByRid(rid);
    }

    //根据公司id，角色id 查询出当前选中公司的未给当前角色授权的用户
    @RequestMapping("selectNoRole")
    public List<SysUser> selectNoRole(long rid, long oid) {
        return service.selectNoRole(rid, oid);
    }
}
