package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.SysResource;
import com.dfbz.entity.SysUser;
import com.dfbz.service.SysResourceService;
import com.dfbz.service.SysUserService;
import com.dfbz.util.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/29 19
 */
@RestController
public class mainController {

    @Autowired
    SysUserService userService;
    @Autowired
    SysResourceService resourceService;

    /**
     * 登录：
     * 1.校验验证码
     * 2.校验登录账号密码
     * 3.将用户信息放入状态管理、将用户的访问资源放入状态管理
     * 4.将用户登录情况返回页面
     *
     * @return
     */

    @RequestMapping("login")
    public Result login(@RequestBody Map<String, Object> params, HttpSession session) {
        Result result = new Result();
        if (params.containsKey("code") && !StringUtils.isEmpty(params.get("code"))) {
            if (session.getAttribute("checkCode").equals(params.get("code"))) {
                if (params.containsKey("username") && !StringUtils.isEmpty(params.get("username")) &&
                        params.containsKey("password") && !StringUtils.isEmpty(params.get("password"))) {
                    SysUser user = new SysUser();
                    String username = (String) params.get("username");
                    String password = (String) params.get("password");
                    user.setUsername(username);
                    user.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(password)+username));
                    SysUser loginUser = userService.selectOne(user);//查询当前角色的所有信息
                    if (loginUser!=null)
                    {
                        result.setSuccess(true);
                        result.setMsg("登录成功！");
                        HashMap<String,Object> map = new HashMap<>();
                        map.put("username",username);
                        map.put("id",loginUser.getId());

                        //查询用户的所有资源权限，放入result
                        List<SysResource> sysResources = resourceService.selectAllByUid(loginUser.getId());//查询用户的所有资源权限
                        map.put("resources",sysResources);
                        result.setObj(map);


                        //将用户信息放入session
                        session.setAttribute("user",user);
                        session.setAttribute("resources",sysResources);
                    }

                }
            }
        }
        return result;
    }
}
