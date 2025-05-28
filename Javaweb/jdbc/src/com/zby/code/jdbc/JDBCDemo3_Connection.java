package com.zby.code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * JDBCDemo3_Connection
 * */
public class JDBCDemo3_Connection {
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
        String sql1 = "UPDATE account SET money = 3000 WHERE id = 1";
        String sql2 = "UPDATE account SET money = 2000 WHERE id = 2";
        // 获取执行对象
        Statement statement = connection.createStatement();
        // 执行 SQL 并获取返回值
        try {
            //开启事务
            connection.setAutoCommit(false);
            int count1 = statement.executeUpdate(sql1);
            System.out.println("受影响的行数: " + count1);
            // int i = 5/0;
            int count2 = statement.executeUpdate(sql2);
            System.out.println("受影响的行数: " + count2);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
        // 关闭 statement
        statement.close();
        // 关闭 connection
        connection.close();
    }
}
