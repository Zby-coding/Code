package com.zby.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
@ComponentScan("com.zby")

public class MyBatisConfig {

    @Bean
    public SqlSessionFactoryBean sessionFactory(DataSource dataSource){
        SqlSessionFactoryBean ssfb= new SqlSessionFactoryBean();
        ssfb.setTypeAliasesPackage("com.zby.domain");
        ssfb.setDataSource(dataSource);

        return ssfb;
    }


    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer msc= new MapperScannerConfigurer();
        msc.setBasePackage("com.zby.dao");
        return msc;
    }

}
