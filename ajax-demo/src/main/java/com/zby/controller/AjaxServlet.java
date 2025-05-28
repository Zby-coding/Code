package com.zby.controller;

@javax.servlet.annotation.WebServlet("/ajaxServlet")
public class AjaxServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        System.out.println("doPost...");
        String username = request.getParameter("username");
        response.getWriter().write(username);

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doPost(request, response);

    }
}
