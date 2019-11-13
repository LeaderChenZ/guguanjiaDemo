package com.dfbz.config;

/**
 * @author Chen
 * @description springmvc配置
 * @date 2019/11/12 17
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.dfbz.controller")
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {


    @Override//放行静态资源
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
