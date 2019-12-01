package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.SysRole;
import com.dfbz.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/27 16
 */
@RestController
@RequestMapping("manager/role")
public class SuyRoleController {
    @Autowired
    SysRoleService service;

    @RequestMapping("list")
    public PageInfo<SysRole> list(@RequestBody Map<String, Object> params) {

        return service.selectByCondition(params);
    }

    @RequestMapping("toDelete")
    public Result toDelete(long id) {
        SysRole user = new SysRole();
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

    @RequestMapping("updateByUids")
    public Result updateByUids(@RequestBody Map<String, Object> params) {
        List<Integer> list = (List<Integer>) params.get("uids");
        int rid = (int) params.get("rid");
        long[] uids = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            uids[i] = Integer.valueOf(list.get(i));
        }
        Result result = new Result();
        int i = service.updateByUids(rid, uids);
        if (i > 0) {
            result.setMsg("更新成功！");
            result.setSuccess(true);
        }
        return result;
    }
}
