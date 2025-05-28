package com.zby.code.jdbc;

import com.zby.code.pojo.Account;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
* JDBCDemo_ResultSet
* */
public class JDBCDemo5_ResultSet {
    @Test
    public void testResultSet() throws Exception {
        String url = "jdbc:mysql://localhost:3306/testdatabase?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true ";
        String username = "root";
        String password = "123456";
        // 获取连接
        Connection connection = DriverManager.getConnection(url,username,password);
        // sql
        String sql = "select * from account";
        // 获取执行sql的对象
        Statement statement = connection.createStatement();
        // 执行查询sql
        ResultSet resultSet = statement.executeQuery(sql);
        // 遍历结果集
        while(resultSet.next()){
            int id = resultSet.getInt(1);
            String name= resultSet.getString(2);
            double money = resultSet.getDouble(3);
            System.out.println(id);
            System.out.println(name);
            System.out.println(money);

            System.out.println("-----------------------------");
        }
        // 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
    @Test
    public void testRs() throws Exception {
        String url = "jdbc:mysql://localhost:3306/testdatabase?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true ";
        String username = "root";
        String password = "123456";
        // 获取连接
        Connection connection = DriverManager.getConnection(url,username,password);
        // sql
        String sql = "select * from account";
        // 获取执行sql的对象
        Statement statement = connection.createStatement();
        // 执行查询sql
        ResultSet resultSet = statement.executeQuery(sql);
        List<Account> list = new ArrayList<>();
        // 遍历结果集
        while(resultSet.next()){
            Account account = new Account();
            account.setId(resultSet.getInt("id"));
            account.setName(resultSet.getString("name"));
            account.setMoney(resultSet.getDouble("money"));
            // System.out.println(account);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            list.add(account);
            for (Account acc:list) {
                System.out.println(acc);
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println(list);
        }
        // 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}
