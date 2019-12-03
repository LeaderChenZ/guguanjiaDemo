package com.dfbz.aspect;

import com.dfbz.entity.SysLog;
import com.dfbz.entity.SysUser;
import com.dfbz.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 系统日志切面类  用于封装通用的日志功能：
 * <p>
 * 1.系统正常操作完成，记录系统正常日志
 * 2.系统出现异常，记录系统异常信息
 */
@Aspect
public class LogAspect {

    @Autowired
    SysLogService logService;

    @Autowired
    HttpServletRequest request;

    @Pointcut("execution(* com.dfbz..*Controller.*(..))")
    public void pointcut() {
    }


    /*
     * 系统正常操作的通知
     * JoinPoint：连接点对象  方法对象
     * obj：当前的连接点对象的返回值
     * 1.获取SysLog对象需要的数据
     * 2.获取SysLogService
     * 3.插入数据到数据sys_log表
     * */
    @AfterReturning(value = "pointcut()", returning = "obj")
    public void afterRun(JoinPoint joinPoint, Object obj) {
        System.out.println(joinPoint);
        System.out.println("****");
        saveLog(joinPoint, null);
    }


    /*
     * 系统出现异常的通知
     * */
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        saveLog(joinPoint, e);
    }

    /*
     * 配置日志保存方法！
     * */
    public void saveLog(JoinPoint joinPoint, Throwable e) {
        SysLog sysLog = new SysLog();
        sysLog.setType(e == null ? "1" : "2");//根据是否有异常判断属于那种日志类型  1.普通日志  2.异常日志
        if (request != null) {
            HttpSession session = request.getSession();
            SysUser user = (SysUser) session.getAttribute("user");
            if (user != null) {
                sysLog.setCreateBy(user.getName());
            }
            sysLog.setCreateDate(new Date());
            sysLog.setRequestUri(request.getRequestURI());
            sysLog.setRemoteAddr(request.getRemoteAddr());
            sysLog.setUserAgent(request.getHeader("User-Agent"));//用户浏览器的信息
            sysLog.setMethod(request.getMethod());
        }
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                String type = args[i].getClass().getSimpleName();//获取字节码文件的简单名字
                sb.append("[参数").append((i + 1)).append(",类型").append(type).append(",值:")
                        .append(args[i].toString()).append("]");
            }
            sysLog.setParams(sb.toString());
        }
        sysLog.setException(e == null ? "" : e.toString());
        logService.insert(sysLog);
    }
}
