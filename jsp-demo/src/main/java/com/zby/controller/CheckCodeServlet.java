package com.zby.controller;

import com.zby.util.CheckCodeUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

@javax.servlet.annotation.WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        ServletOutputStream os = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);
        HttpSession session = request.getSession();
        request.setAttribute("checkCode",checkCode);

    }
}
