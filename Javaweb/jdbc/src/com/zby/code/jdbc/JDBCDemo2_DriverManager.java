package com.zby.code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * JDBCDemo2_DriverManager
 * */
public class JDBCDemo2_DriverManager {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 注册驱动
        // Class.forName("com.mysql.jdbc.Driver>"); // 修复驱动类路径
        // 本地连接可以将端口号省略
        String url = "jdbc:mysql://localhost:3306/testdatabase?serverTimezone=UTC&useSSL=false";
        String username = "root";
        String password = "123456";
        // 获取连接
        Connection connection = DriverManager.getConnection(url, username, password);
        // 定义 SQL
        String sql = "UPDATE account SET money = 2000 WHERE id = 1";
        // 获取执行对象
        Statement statement = connection.createStatement();
        // 执行 SQL 并获取返回值
        int count = statement.executeUpdate(sql);
        System.out.println("受影响的行数: " + count);
        // 关闭 statement
        statement.close();
        // 关闭 connection
        connection.close();
    }
}
