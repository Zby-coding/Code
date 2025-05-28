package com.zby.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.zby")
@Import(JdbcConfig2.class)
public class SpringConfig {
}
