package com.dfbz.interceptor;

import com.dfbz.entity.SysResource;
import com.dfbz.entity.SysUser;
import com.dfbz.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 拦截器类，普通java类   需要实现HandlerInterceptor接口
 * 实现步骤:
 * 1.编写拦截器类，实现HandlerInterceptor接口
 * 2.重写拦截处理方法
 * 3.将拦截器类交由spring 容器管理
 * 4.配置拦截器拦截逻辑，注册拦截器到spring中  ，并且设置拦截和放行规则
 */
public class ResourceInterceptor implements HandlerInterceptor {

    @Autowired
    SysResourceService resourceService;

    /*
     * 请求到达，方法执行前执行拦截方法
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("user");
        boolean hasResource = false;//定义为不存在

        if (!StringUtils.isEmpty(user)) {  //已经登录了
            //获取系统的所有资源权限，获取用户请求路径进行判断，如果不在系统所有资源权限的范围的放行（未在sys_resources中的资源路径不拦截）
            List<SysResource> sysResources = resourceService.selectAll();//可以从cache中获取
            for (SysResource sysResource : sysResources) {
                if (!StringUtils.isEmpty(sysResource.getUrl()) && requestURI.contains(sysResource.getUrl())) {//存在就结束
                    hasResource = true;//找到有，需要进一步进行权限校验
                    break;
                }
            }
            if (!hasResource) {//没有找到则表示不存在与sys_resources中，就放行
                return true;
            } else {
                //获取用户的所有资源权限（session中存放的），获取用户请求的路径进行判断：
                List<SysResource> resources = (List<SysResource>) session.getAttribute("resources");
                for (SysResource resource : resources) {
                    if (!StringUtils.isEmpty(resource.getUrl()) && requestURI.contains(resource.getUrl())) {//如果权限存在就返回true
                        hasResource = true;//有权限
                        return true;
                    }
                }
                request.getRequestDispatcher("/html/index.html").forward(request, response);
            }

        } else { //未登陆
            request.getRequestDispatcher("/html/login.html").forward(request, response);

        }
        return false;
    }


    /*
     *请求到达，方法执行后，返回视图解析器前的执行拦截方法
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("请求到达，方法执行后，返回视图解析器前的执行拦截方法");
    }

    /*
     * 请求到达后，方法执行后，返回视图解析器，并且响应视图到页面拦截方法
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("请求到达后，方法执行后，返回视图解析器，并且响应视图到页面拦截方法");

    }
}
