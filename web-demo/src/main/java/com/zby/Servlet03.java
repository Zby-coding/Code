package com.zby;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/demo03")
public class Servlet03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getQueryString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        resp.setCharacterEncoding("utf-8");
        String name = req.getParameter("username");
        resp.setHeader("Content-Type", "text/html;charset=utf-8");
        resp.getWriter().write("<h1>" + name + "，欢迎您！~</h1>");*/
        System.out.println(req.getContextPath()); // 虚拟目录 项目访问路径
        System.out.println(req.getMethod()); // 获取请求的方法
        System.out.println(Arrays.toString(req.getCookies())); // 获取cookies
        System.out.println(req.getRequestURL()); // 获取统一资源定位符
        System.out.println(req.getRequestURI()); // 获取统一资源标识符
        System.out.println(req.getHeader("User-agent"));
        BufferedReader reader = req.getReader(); // 获取字符流
        System.out.println(reader.readLine());
    }
}
