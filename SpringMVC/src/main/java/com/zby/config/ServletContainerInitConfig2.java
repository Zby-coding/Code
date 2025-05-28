package com.zby.config;

public class ServletContainerInitConfig2 {/*extends AbstractDispatcherServletInitializer {
    //加载容器对象
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        // 通过上下文注册bean
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // 读取配置文件
        context.register(SpringmvcConfig.class);
        return context;
    }

    // 管理请求
    @Override
    protected String[] getServletMappings() {
        // 所有请求被管理
        return new String[]{"/"};
    }

    // 对配置的容器对象
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        // 通过上下文注册bean
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        // 读取配置文件
        context.register(SpringConfig.class);
        return context;
    }*/
}
