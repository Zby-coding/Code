package com.zby.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

//@Configuration
@ComponentScan("com.zby")
public class JdbcConfig {
    @Value("com.mysql.jdbc.Driver>")
    private String driver;
    @Value("jdbc:mysql://localhost:3306/testdatabase")
    private String url;
    @Value("root")
    private String username;
    @Value("123")
    private String password;

   // @Bean
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}
