package com.zby.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
/**
 * SpringMVC配置类 放行静态资源
 * 步骤：
 * 1.继承WebMvcConfigurationSupport
 * 2.重写addResourceHandlers方法
 * 3.配置静态资源映射
 */

@Configuration
public class SpringMvcSupport extends WebMvcConfigurationSupport {

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("pages/**").addResourceLocations("/pages/");
        registry.addResourceHandler("js/**").addResourceLocations("/js/");
        registry.addResourceHandler("css/**").addResourceLocations("/css/");
        registry.addResourceHandler("plugins/**").addResourceLocations("/plugins/");
    }
}
