package com.zby.controller;

import com.alibaba.fastjson.JSON;
import com.zby.pojo.Brand;
import com.zby.service.BrandService;

import java.io.BufferedReader;

@javax.servlet.annotation.WebServlet("/addBrandServlet")
public class AddBrandServlet extends javax.servlet.http.HttpServlet {
    private BrandService brandService = new BrandService();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        brandService.add(brand);
        response.getWriter().write("success");
    }
}
