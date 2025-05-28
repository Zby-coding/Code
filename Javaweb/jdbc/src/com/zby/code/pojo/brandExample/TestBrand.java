package com.zby.code.pojo.brandExample;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.zby.code.pojo.brandExample.Brand;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestBrand {
    @Test
    public void selectAll() throws Exception {
        Class.forName("com.mysql.jdbc.Driver>");
        String url = "jdbc:mysql://localhost:3306/testdatabase?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true ";
        String userName = "root";
        String password = "123456";
        Connection connection = DriverManager.getConnection(url, userName, password);
        String sql = "select * from tb_brand";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Object> list = new ArrayList<>();
        while (resultSet.next()) {
            Brand brand = new Brand();
            brand.setId(resultSet.getInt("id"));
            brand.setBrandName(resultSet.getString("brand_name"));
            brand.setCompanyName(resultSet.getString("company_name"));
            brand.setOrdered(resultSet.getInt("ordered"));
            brand.setDescription(resultSet.getString("description"));
            brand.setStatus(resultSet.getInt("status"));
            list.add(brand);
        }
        System.out.println(list);
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void selectAll_Druid() throws Exception {
        System.out.println(System.getProperty("user.dir"));
        // 加载配置文件
        Properties properties = new Properties();
        properties.load(new FileReader("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        String sql = "select * from tb_brand";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Object> list = new ArrayList<>();
        while (resultSet.next()) {
            Brand brand = new Brand();
            brand.setId(resultSet.getInt("id"));
            brand.setBrandName(resultSet.getString("brand_name"));
            brand.setCompanyName(resultSet.getString("company_name"));
            brand.setOrdered(resultSet.getInt("ordered"));
            brand.setDescription(resultSet.getString("description"));
            brand.setStatus(resultSet.getInt("status"));
            list.add(brand);
        }
        System.out.println(list);
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
    @Test
    public void insert_Druid() throws Exception {
        String brandName = "盼盼";
        String companyName = "盼盼食品";
        int ordered = 300;
        String description = "好吃不贵，真的美味！！！";
        int status = 1;
        // 加载配置文件
        Properties properties = new Properties();
        properties.load(new FileReader("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        String sql = "insert into tb_brand(brand_name,company_name,ordered,description,status) values(?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,brandName);
        preparedStatement.setString(2,companyName);
        preparedStatement.setInt(3,ordered);
        preparedStatement.setString(4,description);
        preparedStatement.setInt(5,status);
        int count = preparedStatement.executeUpdate();
        System.out.println(count > 0 ? "成功" : "失败");
        preparedStatement.close();
        connection.close();
    }
    @Test
    public void update_druid() throws Exception {
        String brandName = "盼盼";
        String companyName = "盼盼食品";
        int ordered = 800;
        String description = "好吃不贵，真的美味！！！";
        int status = 1;
        int id = 4;
        Properties properties = new Properties();
        properties.load(new FileReader("src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        String sql = "update tb_brand set brand_name = ?,company_name = ?,ordered = ?,description = ?,status = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,brandName);
        preparedStatement.setString(2,companyName);
        preparedStatement.setInt(3,ordered);
        preparedStatement.setString(4,description);
        preparedStatement.setInt(5,status);
        preparedStatement.setInt(6,id);
        int count = preparedStatement.executeUpdate();
        System.out.println(count);
        preparedStatement.close();
        connection.close();
    }
    @Test
    public void delete_druid() throws Exception {
        int id = 4;
        Properties properties = new Properties();
        properties.load(new FileReader("src/druid.properties"));
        String sql = "delete from tb_brand where id = ?";
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1,id);
        int i = preparedStatement.executeUpdate();
        System.out.println(i > 0 ? "成功" : "失败");
        preparedStatement.close();
        connection.close();
    }
}
