package com.zby.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
//@Component
//@ComponentScan("com.zby")
public class MybatisConfig {
    @Bean // 用于在配置类中声明是一个bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setTypeAliasesPackage("com.zby.domain");
        ssfb.setDataSource(dataSource);
        return ssfb;
    }

    @Bean
    // 配置扫描dao接口的包，生成dao接口的代理对象，并注入到spring容器中
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer msc = new MapperScannerConfigurer();
        msc.setBasePackage("com.zby.dao");
        return msc;
    }
}



