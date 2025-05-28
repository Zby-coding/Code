package com.zby.controller;

import com.zby.pojo.Brand;
import com.zby.service.BrandService;

import java.util.List;

@javax.servlet.annotation.WebServlet("/allServlet")
public class BrandServletAll extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        BrandService brandService = new BrandService();
        List<Brand> brands = brandService.selectAll();
        request.setAttribute("bs", brands);
        System.out.println("allServlet");
        request.getRequestDispatcher("/brand.jsp").forward(request, response);
    }
}