package com.zby.code.jdbc;

import com.zby.code.pojo.Account;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * JDBCDemo_ResultSet
 * */
public class JDBCDemo6_PreparedStatement {
    @Test
    public void testLogin() throws Exception {
        String url = "jdbc:mysql://localhost:3306/testdatabase?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true ";
        String username = "root";
        String password = "123456";
        // 获取连接
        Connection connection = DriverManager.getConnection(url, username, password);
        String name2 = "zhangsan";
        String pwd = "'or '1'='1";
        // String pwd = "123456";
        // sql
        String sql = "select * from tb_user where username='" + name2 + "' and pwd='" + pwd + "'";
        System.out.println(sql);// 获取执行sql的对象
        Statement statement = connection.createStatement();
        // 执行查询sql
        ResultSet resultSet = statement.executeQuery(sql);
        // 遍历结果集
        if (resultSet.next()) System.out.println("成功");
        else System.out.println("失败");

        // 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void testLogin2() throws Exception {
        String url = "jdbc:mysql://localhost:3306/testdatabase?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true ";
        String username = "root";
        String password = "123456";
        // 获取连接
        Connection connection = DriverManager.getConnection(url, username, password);
        String name = "zhangsan";
        String pwd = "'or '1'='1";
        // sql
        String sql = "select * from tb_user where username=? and pwd=?";
        System.out.println(sql);// 获取执行sql的对象
        // 获取执行对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, pwd);
        // 获取pstatement 执行查询sql
        ResultSet resultSet = preparedStatement.executeQuery();
        // 遍历结果集
        if (resultSet.next()) System.out.println("成功");
        else System.out.println("失败");

        // 释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
