package com.zby.controller;

@javax.servlet.annotation.WebServlet("/selectUserServlet")
public class SelectUserServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        // 接收用户输入的用户名
        String username = request.getParameter("username");
        // 判断 从service中获取的返回值
        boolean flag = true;
        // 响应值
        response.getWriter().write("" + flag);

    }
}
