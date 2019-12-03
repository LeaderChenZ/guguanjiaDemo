package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.SysArea;
import com.dfbz.service.SysAreaService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/19 18
 */
@RestController
@RequestMapping("manager/area")
public class SysAreaController {
    @Autowired
    SysAreaService service;

    @RequestMapping("")
    public ModelAndView toIndex(){
        return new ModelAndView("/html/area/area.html");
    }
    @RequestMapping("list")
    public List<SysArea> selectAll() {
        return service.selectAll();
    }

    @RequestMapping("index")
    public PageInfo<SysArea> index(@RequestBody Map<String, Object> params) {
        return service.selectByCondition(params);
    }

    @RequestMapping("selectPId")
    public List<SysArea> selectPId(long aid) {
        return service.selectByPid(aid);
    }

    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) throws Exception {
        //在response输出之前，设置输出的格式
        //默认不支持中文，new String（fname，getBytes（），"ISO-8859-1"),转义中文编码
        response.addHeader("Content-Disposition", "attachment;filename=" + new String("area.xls".getBytes(), "ISO-8859-1"));
        //将文件写入到response的输出流
        service.getListOutputStream(response.getOutputStream());
    }


    /*
     * 写入对象
     * */
    @RequestMapping("importExcel")
    public Result importExcel(MultipartFile file) throws Exception {
        int i = service.importExcel(file.getInputStream());
        Result result = new Result();
        if (i > 0) {
            result.setMsg("操作成功");
            result.setSuccess(true);
        }
        return result;

    }

    /*
     * 查询sys_area id对应的值
     * */

    @RequestMapping("toUpdate")
    public SysArea toUpdate(long areaId) {
        return service.selectByAreaId(areaId);
    }


   /*TODO 更新  未完成*/

    @RequestMapping("update")
    public Result update(@RequestBody  SysArea obj) {
        int i = service.updateArea(obj);
        Result result = new Result();
        if (i > 0) {
            result.setMsg("操作成功！！");
            result.setSuccess(true);
        }
        return result;
    }

}
