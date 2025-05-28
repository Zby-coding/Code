package com.zby.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan({"com.zby.controller","com.zby.config"})
@EnableWebMvc
public class SpringMvcConfig {
}
