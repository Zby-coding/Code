package com.zby;

@javax.servlet.annotation.WebServlet("/resp02")
public class ResponseDemo02 extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        /*
        解决中文乱码的三种方式:
        1. response.setCharacterEncoding("utf-8");
        2. response.setContentType("text/html;charset=utf-8");
        3. response.setHeader("Content-Type", "text/html;charset=utf-8");
        */

        response.setStatus(200);
        System.out.println("ResponseDemo02.doGet");
        System.out.println(request.getAttribute("msg"));
        response.setHeader("Content-Type", "text/html;charset=utf-8");
        response.getWriter().write("<h1>ResponseDemo02.doGet222222</h1>");
    }
}