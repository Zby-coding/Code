package com.zby;

import com.zby.config.SpringConfig;
import com.zby.service.ResourcesService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
        ResourcesService service = ctx.getBean(ResourcesService.class);
        System.out.println(service.openURL("http://www.baidu.com", "root "));
    }
}
