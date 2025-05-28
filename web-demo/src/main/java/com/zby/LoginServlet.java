package com.zby;

import com.zby.mapper.UserMapper;
import com.zby.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.io.PrintWriter;

@javax.servlet.annotation.WebServlet("/Login")
public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        // 获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 判断是否正确与数据库表链接
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        // 获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        // 获取mapper对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.select(username, password);
        response.setHeader("Content-Type", "text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (user != null) {
            writer.write("登陆成功");
        } else {
            writer.write("登陆失败");
        }

        sqlSession.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

    }
}