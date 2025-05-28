package com.zby.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@ComponentScan("com.zby")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Transactional
@EnableTransactionManagement
public class SpringConfig {
}
