package com.dfbz.config;

/**
 * @author Chen
 * @description springmvc配置
 * @date 2019/11/12 17
 */

import com.dfbz.aspect.LogAspect;
import com.dfbz.interceptor.ResourceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.dfbz.controller")
@EnableWebMvc
@EnableAspectJAutoProxy
public class SpringMvcConfig implements WebMvcConfigurer {

    @Autowired
    private ResourceInterceptor resourceInterceptor;//注入拦截器

    /**
     * springmvc文件上传：
     * 1.前端处理
     * 2.配置springmvc文件上传解析器  multiparResolerver
     * 3.接收文件上传处理方法上添加 MultipartFile  接收请求数据
     *
     * @param configurer
     */
    @Bean("multipartResolver")//必须指定bean命名
    public CommonsMultipartResolver getMultipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();

        return commonsMultipartResolver;
    }

    @Override//放行静态资源
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //获取拦截器注册对象
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(resourceInterceptor);
        interceptorRegistration.addPathPatterns(new String[]{"/**"});

        //要忽略的拦截请求
        interceptorRegistration.excludePathPatterns(new String[]{"/html/login.html", "/html/index.html", "/login"});
    }


    /**
     * 创建切面类组件对象，并交由spring父容器管理
     */
    @Bean
    public LogAspect getlogAspect()
    {
        return new LogAspect();
    }
}
