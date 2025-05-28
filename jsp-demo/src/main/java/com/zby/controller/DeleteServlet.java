package com.zby.controller;

import com.zby.service.BrandService;

@javax.servlet.annotation.WebServlet("/deleteServlet")
public class DeleteServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        BrandService brandService = new BrandService();
        brandService.delete(Integer.parseInt(request.getParameter("id")));

    }
}