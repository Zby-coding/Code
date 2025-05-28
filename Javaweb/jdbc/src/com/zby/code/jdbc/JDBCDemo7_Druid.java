package com.zby.code.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
* 连接池 德鲁伊
* */
public class JDBCDemo7_Druid {
    public static void main(String[] args) throws Exception {
        // 1,加载jar包
        // 2.加载配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("jdbc/src/druid.properties"));
        // 3.加载配置文件
        //3.获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);


    }
}
