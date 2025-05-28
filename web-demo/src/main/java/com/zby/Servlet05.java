package com.zby;

import java.nio.charset.StandardCharsets;

// Tomcat8 默认服务器编码和浏览器解码方式都是utf-8
@javax.servlet.annotation.WebServlet("/demo05")
public class Servlet05 extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        this.doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        String username = request.getParameter("username");
        System.out.println(new String(username.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        System.out.println(request.getAttribute("msg"));

    }
}