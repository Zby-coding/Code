package com.zby;

import com.zby.config.SpringConfig;
import com.zby.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
         UserService bean = context.getBean(UserService.class);
        // UserController bean = context.getBean(UserController.class);
        System.out.println(bean);

    }
}
